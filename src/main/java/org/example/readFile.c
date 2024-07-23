#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// 定义一个结构体来表示链表中的节点
typedef struct Node {
    char data[100];
    struct Node* next;
} Node;

// 函数声明
Node* createNode(char* data);
void insertNode(Node** head, char* data);
void sortList(Node* head);
void printList(Node* head);
void writeToFile(Node* head, const char* filename);
void freeList(Node* head);

int main() {
    // 打开输入文件
    FILE* file = fopen("input.txt", "r");
    if (file == NULL) {
        perror("无法打开输入文件");
        return 1;
    }

    Node* head = NULL;
    char buffer[100];

    // 读取文件中的每一行并插入链表
    while (fgets(buffer, sizeof(buffer), file)) {
        // 去掉换行符
        buffer[strcspn(buffer, "\n")] = 0;
        insertNode(&head, buffer);
    }
    fclose(file);

    // 对链表进行排序
    sortList(head);

    // 打印链表中的内容
    printList(head);

    // 将排序后的内容写入输出文件
    writeToFile(head, "output.txt");

    // 释放链表的内存
    freeList(head);

    return 0;
}

// 创建一个新节点
Node* createNode(char* data) {
}

// 向链表中插入一个新节点
void insertNode(Node** head, char* data) {
    Node* newNode = createNode(data);
    newNode->next = *head;
    *head = newNode;
}

// 对链表进行冒泡排序
void sortList(Node* head) {
    if (head == NULL) return;
    Node* i;
    Node* j;
    char temp[100];
    for (i = head; i != NULL; i = i->next) {
        for (j = i->next; j != NULL; j = j->next) {
            if (strcmp(i->data, j->data) > 0) {
                strcpy(temp, i->data);
                strcpy(i->data, j->data);
                strcpy(j->data, temp);
            }
        }
    }
}

// 打印链表中的所有节点
void printList(Node* head) {
    Node* current = head;
    while (current != NULL) {
        printf("%s\n", current->data);
        current = current->next;
    }
}

// 将链表中的内容写入文件
void writeToFile(Node* head, const char* filename) {
    FILE* file = fopen(filename, "w");
    if (file == NULL) {
        perror("无法打开输出文件");
        return;
    }
    Node* current = head;
    while (current != NULL) {
        fprintf(file, "%s\n", current->data);
        current = current->next;
    }
    fclose(file);
}

// 释放链表中的所有节点
void freeList(Node* head) {
    Node* current = head;
    Node* nextNode;
    while (current != NULL) {
        nextNode = current->next;
        free(current);
        current = nextNode;
    }
}
