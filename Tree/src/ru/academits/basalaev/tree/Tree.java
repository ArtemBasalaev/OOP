package ru.academits.basalaev.tree;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Deque;
import java.util.Queue;
import java.util.function.Consumer;

public class Tree<E> {
    private TreeNode<E> root;
    private int size;
    private Comparator<E> comparator;

    public Tree() {
    }

    public Tree(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    public Tree(E data) {
        root = new TreeNode<>(data);
        size++;
    }

    private int compare(E data1, E data2) {
        if (comparator != null) {
            return comparator.compare(data1, data2);
        }

        if (data1 == null && data2 == null) {
            return 0;
        }

        if (data1 == null) {
            return -1;
        }

        if (data2 == null) {
            return 1;
        }

        //noinspection unchecked
        return ((Comparable<E>) data1).compareTo(data2);
    }

    public void insert(E data) {
        TreeNode<E> node = new TreeNode<>(data);

        if (root == null) {
            root = node;

            size++;

            return;
        }

        TreeNode<E> currentNode = root;

        while (true) {
            E currentData = currentNode.getData();

            if (compare(currentData, data) > 0) {
                if (currentNode.getLeft() != null) {
                    currentNode = currentNode.getLeft();
                } else {
                    currentNode.setLeft(node);

                    size++;

                    return;
                }
            } else {
                if (currentNode.getRight() != null) {
                    currentNode = currentNode.getRight();
                } else {
                    currentNode.setRight(node);

                    size++;

                    return;
                }
            }
        }
    }

    public boolean contains(E data) {
        if (root == null) {
            return false;
        }

        TreeNode<E> currentNode = root;

        while (true) {
            E currentData = currentNode.getData();

            int compareResult = compare(currentData, data);

            if (compareResult == 0) {
                return true;
            }

            if (compareResult > 0) {
                if (currentNode.getLeft() != null) {
                    currentNode = currentNode.getLeft();
                } else {
                    return false;
                }
            } else {
                if (currentNode.getRight() != null) {
                    currentNode = currentNode.getRight();
                } else {
                    return false;
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

        while (true) {
            int compareResult = compare(child.getData(), data);

            if (compareResult == 0) {
                break;
            }

            parentNode = child;

            if (compareResult > 0) {
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

                size--;

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

                size--;

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

            minLeftNode.setLeft(child.getLeft());

            if (minLeftNode != child.getRight()) {
                if (minLeftNode.getRight() == null) {
                    parentMinLeftNode.setLeft(null);
                } else {
                    parentMinLeftNode.setLeft(minLeftNode.getRight());
                }

                minLeftNode.setRight(child.getRight());
            }

            if (child == root) {
                root = minLeftNode;

                size--;

                return true;
            }

            if (isLeftChild) {
                parentNode.setLeft(minLeftNode);
            } else {
                parentNode.setRight(minLeftNode);
            }
        }

        size--;

        return true;
    }

    public void visitBreadthFirst(Consumer<E> consumer) {
        if (root == null) {
            return;
        }

        Queue<TreeNode<E>> queue = new ArrayDeque<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode<E> currentNode = queue.remove();

            consumer.accept(currentNode.getData());

            if (currentNode.getLeft() != null) {
                queue.add(currentNode.getLeft());
            }

            if (currentNode.getRight() != null) {
                queue.add(currentNode.getRight());
            }
        }
    }

    public void visitDepthFirst(Consumer<E> consumer) {
        if (root == null) {
            return;
        }

        Deque<TreeNode<E>> stack = new ArrayDeque<>();
        stack.addLast(root);

        while (!stack.isEmpty()) {
            TreeNode<E> currentNode = stack.removeLast();

            consumer.accept(currentNode.getData());

            if (currentNode.getRight() != null) {
                stack.addLast(currentNode.getRight());
            }

            if (currentNode.getLeft() != null) {
                stack.addLast(currentNode.getLeft());
            }
        }
    }

    private void visitDepthFirstWithRecursion(TreeNode<E> node, Consumer<E> consumer) {
        if (node == null) {
            return;
        }

        consumer.accept(node.getData());

        if (node.getLeft() != null) {
            visitDepthFirstWithRecursion(node.getLeft(), consumer);
        }

        if (node.getRight() != null) {
            visitDepthFirstWithRecursion(node.getRight(), consumer);
        }
    }

    public void visitDepthFirstWithRecursion(Consumer<E> consumer) {
        visitDepthFirstWithRecursion(root, consumer);
    }

    public int getSize() {
        return size;
    }
}