package ru.academits.basalaev.tree_main;

import ru.academits.basalaev.tree.Tree;

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

        System.out.println("Количество узлов в дереве: " + tree.getNodesCount());

        if (tree.delete(8)) {
            System.out.println("Узел с указанным значением удален из дерва");
        } else {
            System.out.println("Узла с указанным значением нет в дерве");
        }

        System.out.println("Количество узлов в дереве: " + tree.getNodesCount());

        if (tree.search(15) != null) {
            System.out.println("Узел с указанным значением есть в дереве");
        } else {
            System.out.println("Узла с указанным значением нет в дерве");
        }

        System.out.println("Обход дерева в ширину:");
        tree.visitBreadthFirst();

        System.out.println("Обход дерева в глубину:");
        tree.visitDepthFirst();

        System.out.println("Обход дерева в глубину с помощью рекурсии:");
        tree.visitDepthFirstWithRecursion(tree.getRoot());
    }
}