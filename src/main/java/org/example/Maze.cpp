#include <iostream>
#include <vector>
#include <stack>
#include <ctime>
#include <cstdlib>

// 迷宫生成和求解

class Maze {
public:
    Maze(int width, int height) : width(width), height(height), maze(width, std::vector<Cell>(height)) {
        std::srand(std::time(nullptr));
        generateMaze();
    }

    void displayMaze() const {
    }

    bool solveMaze() {
        std::stack<std::pair<int, int>> path;
        path.push({0, 0});
        maze[0][0].visited = true;

        while (!path.empty()) {
            auto [x, y] = path.top();

            if (x == width - 1 && y == height - 1) {
                return true; // Reached the exit
            }

            bool moved = false;
            for (auto [dx, dy] : directions) {
                int nx = x + dx, ny = y + dy;
                if (isValid(nx, ny) && !maze[nx][ny].isWall && !maze[nx][ny].visited) {
                    path.push({nx, ny});
                    maze[nx][ny].visited = true;
                    moved = true;
                    break;
                }
            }

            if (!moved) {
                path.pop();
            }
        }

        return false; // No solution found
    }

private:
    struct Cell {
        bool isWall = true;
        bool visited = false;
    };

    int width, height;
    std::vector<std::vector<Cell>> maze;
    const std::vector<std::pair<int, int>> directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    void generateMaze() {
        std::stack<std::pair<int, int>> path;
        path.push({0, 0});
        maze[0][0].isWall = false;

        while (!path.empty()) {
            auto [x, y] = path.top();
            maze[x][y].visited = true;

            std::vector<std::pair<int, int>> neighbors;
            for (auto [dx, dy] : directions) {
                int nx = x + dx * 2, ny = y + dy * 2;
                if (isValid(nx, ny) && maze[nx][ny].isWall) {
                    neighbors.push_back({dx, dy});
                }
            }

            if (!neighbors.empty()) {
                auto [dx, dy] = neighbors[std::rand() % neighbors.size()];
                int nx = x + dx, ny = y + dy;
                maze[nx][ny].isWall = false;
                maze[x + dx * 2][y + dy * 2].isWall = false;
                path.push({x + dx * 2, y + dy * 2});
            } else {
                path.pop();
            }
        }
    }

    bool isValid(int x, int y) const {
        return x >= 0 && x < width && y >= 0 && y < height;
    }
};

int main() {
    int width = 21;
    int height = 21;
    Maze maze(width, height);

    std::cout << "Generated Maze:" << std::endl;
    maze.displayMaze();

    if (maze.solveMaze()) {
        std::cout << "Maze solved!" << std::endl;
    } else {
        std::cout << "No solution found for the maze." << std::endl;
    }

    return 0;
}
