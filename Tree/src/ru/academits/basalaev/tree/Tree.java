package ru.academits.basalaev.tree;

import java.util.ArrayDeque;
import java.util.Stack;

public class Tree<E extends Comparable<E>> {
    private TreeNode<E> root;
    private int nodesCount;

    public Tree() {
    }

    public Tree(E data) {
        root = new TreeNode<>(data);
        nodesCount++;
    }

    public TreeNode<E> getRoot() {
        return root;
    }

    public void insert(E data) {
        TreeNode<E> node = new TreeNode<>(data);

        if (root == null) {
            root = node;

            return;
        }

        TreeNode<E> currentNode = root;

        while (true) {
            E currentData = currentNode.getData();

            if (currentData.compareTo(data) >= 0) {
                if (currentNode.getLeft() != null) {
                    currentNode = currentNode.getLeft();
                } else {
                    currentNode.setLeft(node);

                    nodesCount++;

                    return;
                }
            } else {
                if (currentNode.getRight() != null) {
                    currentNode = currentNode.getRight();
                } else {
                    currentNode.setRight(node);

                    nodesCount++;

                    return;
                }
            }
        }
    }

    public TreeNode<E> search(E data) {
        if (root == null) {
            return null;
        }

        TreeNode<E> currentNode = root;

        while (true) {
            E currentData = currentNode.getData();

            if (currentData.compareTo(data) == 0) {
                return currentNode;
            }

            if (currentData.compareTo(data) > 0) {
                if (currentNode.getLeft() != null) {
                    currentNode = currentNode.getLeft();
                } else {
                    return null;
                }
            } else {
                if (currentNode.getRight() != null) {
                    currentNode = currentNode.getRight();
                } else {
                    return null;
                }
            }
        }
    }

    public boolean delete(E data) {
        if (root == null) {
            return false;
        }

        TreeNode<E> parentNode = root;
        TreeNode<E> child = parentNode;

        boolean isLeftChild = false;

        while (child.getData().compareTo(data) != 0) {
            parentNode = child;

            if (parentNode.getData().compareTo(data) > 0) {
                child = parentNode.getLeft();
                isLeftChild = true;
            } else {
                child = parentNode.getRight();
                isLeftChild = false;
            }

            if (child == null) {
                return false;
            }
        }

        if (child.getLeft() == null && child.getRight() == null) {
            if (child == root) {
                root = null;

                nodesCount--;

                return true;
            }

            if (isLeftChild) {
                parentNode.setLeft(null);
            } else {
                parentNode.setRight(null);
            }
        } else if (child.getLeft() == null || child.getRight() == null) {
            if (child == root) {
                if (root.getLeft() != null) {
                    root = root.getLeft();
                } else {
                    root = root.getRight();
                }

                nodesCount--;

                return true;
            }

            if (isLeftChild) {
                if (child.getLeft() != null) {
                    parentNode.setLeft(child.getLeft());
                } else {
                    parentNode.setLeft(child.getRight());
                }
            } else {
                if (child.getLeft() != null) {
                    parentNode.setRight(child.getLeft());
                } else {
                    parentNode.setRight(child.getRight());
                }
            }
        } else {
            TreeNode<E> parentMinLeftNode = child;
            TreeNode<E> minLeftNode = child.getRight();

            while (minLeftNode.getLeft() != null) {
                parentMinLeftNode = minLeftNode;
                minLeftNode = parentMinLeftNode.getLeft();
            }

            if (minLeftNode.getRight() == null) {
                parentMinLeftNode.setLeft(null);
            } else {
                parentMinLeftNode.setLeft(minLeftNode.getRight());
            }

            minLeftNode.setRight(child.getRight());
            minLeftNode.setLeft(child.getLeft());

            if (child == root) {
                root = minLeftNode;

                nodesCount--;

                return true;
            }

            if (isLeftChild) {
                parentNode.setLeft(minLeftNode);
            } else {
                parentNode.setRight(minLeftNode);
            }
        }

        nodesCount--;

        return true;
    }

    public void visitBreadthFirst() {
        ArrayDeque<TreeNode<E>> queue = new ArrayDeque<>();
        queue.addFirst(root);

        while (!queue.isEmpty()) {
            TreeNode<E> currentNode = queue.pop();

            System.out.println(currentNode.getData());

            if (currentNode.getLeft() != null) {
                queue.addLast(currentNode.getLeft());
            }

            if (currentNode.getRight() != null) {
                queue.addLast(currentNode.getRight());
            }
        }
    }

    public void visitDepthFirst() {
        Stack<TreeNode<E>> stack = new Stack<>();

        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode<E> currentNode = stack.pop();

            System.out.println(currentNode.getData());

            if (currentNode.getRight() != null) {
                stack.push(currentNode.getRight());
            }

            if (currentNode.getLeft() != null) {
                stack.push(currentNode.getLeft());
            }
        }
    }

    public void visitDepthFirstWithRecursion(TreeNode<E> node) {
        System.out.println(node.getData());

        if (node.getLeft() != null) {
            visitDepthFirstWithRecursion(node.getLeft());
        }

        if (node.getRight() != null) {
            visitDepthFirstWithRecursion(node.getRight());
        }
    }

    public int getNodesCount() {
        return nodesCount;
    }
}
