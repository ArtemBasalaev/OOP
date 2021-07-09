package ru.academits.basalaev.csv;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class CsvFileToHtml {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Входных аргументов нет, используйте команду /help для получения справки");

            return;
        }

        if (args.length == 1 && args[0].equals("/help")) {
            System.out.println("Программа принимает два аргумента:");
            System.out.println("\tпервый аргумент - путь к файлу с таблицей в формате сsv;");
            System.out.println("\tвторой аргумент - путь и название создаваемого файла с таблицей в формате html.");
            System.out.println("Формат входного и выходного файлов .txt, используите символ \",\" для разделения полей в сsv");

            return;
        }

        if (args.length == 1) {
            System.out.println("Аргумент указан не верно. Используйте команду /help для получения справки");

            return;
        }

        try (Scanner scanner = new Scanner(new FileInputStream(args[0]));
             PrintWriter writer = new PrintWriter(args[1])) {
            StringBuilder tableCell = new StringBuilder();

            writer.println("<!DOCTYPE html>");
            writer.println("<html>");
            writer.println("<head>");
            writer.println("\t<meta charset=\"utf-8\">");
            writer.println("</head>");
            writer.println("<body>");
            writer.println("\t<table>");

            int doubleQuotesCountInString = 0;
            int doubleQuotesCountInSubString = 0;

            while (scanner.hasNextLine()) {
                String string = scanner.nextLine();

                if (string.length() == 0) {
                    continue;
                }

                if (doubleQuotesCountInString == 0) {
                    writer.println("\t\t<tr>");
                }

                for (int i = 0; i < string.length(); i++) {
                    char symbol = string.charAt(i);

                    if (symbol == '"') {
                        doubleQuotesCountInSubString++;
                        doubleQuotesCountInString++;
                    }

                    if (symbol == '>') {
                        tableCell.append("&gt;");

                        continue;
                    }

                    if (symbol == '<') {
                        tableCell.append("&lt;");

                        continue;
                    }

                    if (symbol == '&') {
                        tableCell.append("&amp;");

                        continue;
                    }

                    if (tableCell.length() != 0 && i == 0) {
                        tableCell.append("<br/>");
                    }

                    if (doubleQuotesCountInSubString % 2 == 0 && (symbol == ',' || i == string.length() - 1)) {
                        if (symbol != ',') {
                            tableCell.append(string.charAt(i));
                        }

                        if (doubleQuotesCountInSubString != 0) {
                            tableCell.deleteCharAt(tableCell.length() - 1);
                            doubleQuotesCountInSubString = 0;
                        }

                        writer.print("\t\t\t<td>");
                        writer.print(tableCell);
                        writer.println("</td>");

                        tableCell.setLength(0);

                        continue;
                    }

                    if (doubleQuotesCountInSubString % 2 != 0 && symbol == '"') {
                        continue;
                    }

                    tableCell.append(symbol);
                }

                if (doubleQuotesCountInString % 2 == 0) {
                    if (string.charAt(string.length() - 1) == ',') {
                        writer.print("\t\t\t<td>");
                        writer.println("</td>");
                    }

                    writer.println("\t\t</tr>");

                    doubleQuotesCountInString = 0;
                }
            }

            writer.println("\t</table>");
            writer.println("</body>");
            writer.println("</html>");
        } catch (IOException e) {
            System.out.println("Исходный файл " + args[0] + " не найден либо путь для сохранения указан не верно");
        }
    }
}
