package ru.academits.basalaev.tree_main;

import ru.academits.basalaev.tree.Tree;

import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) {
        Tree<Integer> tree = new Tree<>(8);

        tree.insert(3);
        tree.insert(10);
        tree.insert(1);
        tree.insert(6);
        tree.insert(4);
        tree.insert(7);
        tree.insert(14);
        tree.insert(13);
        tree.insert(9);
        tree.insert(15);

        System.out.println("Количество узлов в дереве: " + tree.getSize());

        if (tree.delete(14)) {
            System.out.println("Узел с указанным значением удален из дерва");
        } else {
            System.out.println("Узла с указанным значением нет в дерве");
        }

        System.out.println("Количество узлов в дереве: " + tree.getSize());

        if (tree.contains(15)) {
            System.out.println("Узел с указанным значением есть в дереве");
        } else {
            System.out.println("Узла с указанным значением нет в дерве");
        }

        Consumer<Integer> consumer = System.out::println;

        System.out.println("Обход дерева в ширину:");
        tree.visitBreadthFirst(consumer);

        System.out.println("Обход дерева в глубину:");
        tree.visitDepthFirst(consumer);

        System.out.println("Обход дерева в глубину с помощью рекурсии:");
        tree.visitDepthFirstWithRecursion(consumer);
    }
}