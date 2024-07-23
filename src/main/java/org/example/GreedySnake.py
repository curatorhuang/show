import pygame
import random
import sys

# 设置游戏参数
SCREEN_WIDTH = 800
SCREEN_HEIGHT = 600
SNAKE_SIZE = 20
APPLE_SIZE = 20
SPEED = 10

# 设置颜色
BLACK = (0, 0, 0)
WHITE = (255, 255, 255)
RED = (255, 0, 0)

# 初始化 pygame
pygame.init()
screen = pygame.display.set_mode((SCREEN_WIDTH, SCREEN_HEIGHT))
pygame.display.set_caption('贪吃蛇')

# 设置蛇的初始位置和方向
snake = [(100, 100), (90, 100), (80, 100)]
direction = 'right'

# 设置苹果的位置
apple = (random.randint(0, SCREEN_WIDTH - APPLE_SIZE) // APPLE_SIZE * APPLE_SIZE,
         random.randint(0, SCREEN_HEIGHT - APPLE_SIZE) // APPLE_SIZE * APPLE_SIZE)

# 游戏主循环
while True:
    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            pygame.quit()
            sys.exit()
        elif event.type == pygame.KEYDOWN:
            if event.key == pygame.K_UP and direction != 'down':
                direction = 'up'
            elif event.key == pygame.K_DOWN and direction != 'up':
                direction = 'down'
            elif event.key == pygame.K_LEFT and direction != 'right':
                direction = 'left'
            elif event.key == pygame.K_RIGHT and direction != 'left':
                direction = 'right'

    # 更新蛇的位置
    head = snake[0]
    if direction == 'up':
        new_head = (head[0], head[1] - SNAKE_SIZE)
    elif direction == 'down':
        new_head = (head[0], head[1] + SNAKE_SIZE)
    elif direction == 'left':
        new_head = (head[0] - SNAKE_SIZE, head[1])
    elif direction == 'right':
        new_head = (head[0] + SNAKE_SIZE, head[1])
    snake.insert(0, new_head)

    # 检查蛇是否吃到苹果
    if snake[0] == apple:
        apple = (random.randint(0, SCREEN_WIDTH - APPLE_SIZE) // APPLE_SIZE * APPLE_SIZE,
                 random.randint(0, SCREEN_HEIGHT - APPLE_SIZE) // APPLE_SIZE * APPLE_SIZE)
    else:
        snake.pop()

    # 检查蛇是否撞墙或自己

    # 绘制游戏界面
    screen.fill(BLACK)
    for pos in snake:
        pygame.draw.rect(screen, WHITE, (pos[0], pos[1], SNAKE_SIZE, SNAKE_SIZE))
    pygame.draw.rect(screen, RED, (apple[0], apple[1], APPLE_SIZE, APPLE_SIZE))
    pygame.display.flip()

    # 设置游戏速度
    pygame.time.delay(1000 // SPEED)