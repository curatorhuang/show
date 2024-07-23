import os
import hashlib
import sqlite3
from getpass import getpass

# 环境变量存储敏感信息
DB_PATH = os.getenv('DB_PATH', 'users.db')
SECRET_KEY = os.getenv('SECRET_KEY', 'my_secret_key')

# 数据库初始化
def init_db():
    with sqlite3.connect(DB_PATH) as conn:
        cursor = conn.cursor()
        cursor.execute('''CREATE TABLE IF NOT EXISTS users
                          (id INTEGER PRIMARY KEY AUTOINCREMENT,
                           username TEXT UNIQUE NOT NULL,
                           password_hash TEXT NOT NULL,
                           is_admin INTEGER NOT NULL DEFAULT 0)''')
        conn.commit()

# 安全的哈希密码函数
def hash_password(password, salt):
    return hashlib.pbkdf2_hmac('sha256', password.encode(), salt, 100000)

# 注册用户


# 验证用户
def authenticate_user(username, password):
    salt = SECRET_KEY.encode()
    password_hash = hash_password(password, salt)
    with sqlite3.connect(DB_PATH) as conn:
        cursor = conn.cursor()
        cursor.execute("SELECT * FROM users WHERE username = ? AND password_hash = ?", (username, password_hash))
        user = cursor.fetchone()
        if user:
            return True
        return False

# 删除用户（权限管理）
def delete_user(username, current_user):
    if not current_user.get('is_admin'):
        raise PermissionError("未经授权的操作")
    with sqlite3.connect(DB_PATH) as conn:
        cursor = conn.cursor()
        cursor.execute("DELETE FROM users WHERE username = ?", (username,))
        conn.commit()

# 获取用户信息（防御性编程）
def get_user_info(username):
    try:
        with sqlite3.connect(DB_PATH) as conn:
            cursor = conn.cursor()
            cursor.execute("SELECT * FROM users WHERE username = ?", (username,))
            user = cursor.fetchone()
            if user:
                return {
                    'id': user[0],
                    'username': user[1],
                    'is_admin': bool(user[3])
                }
            else:
                return None
    except sqlite3.Error as e:
        print(f"数据库错误: {e}")
        return None

if __name__ == "__main__":
    init_db()
    while True:
        action = input("选择操作: [register, login, delete, info, quit]: ").strip().lower()
        if action == "register":
            username = input("输入用户名: ").strip()
            password = getpass("输入密码: ").strip()
            is_admin = input("是否管理员? [yes/no]: ").strip().lower() == 'yes'
            register_user(username, password, is_admin)
        elif action == "login":
            username = input("输入用户名: ").strip()
            password = getpass("输入密码: ").strip()
            if authenticate_user(username, password):
                print("登录成功")
            else:
                print("登录失败")
        elif action == "delete":
            current_username = input("输入当前用户名: ").strip()
            current_password = getpass("输入当前密码: ").strip()
            if authenticate_user(current_username, current_password):
                user_info = get_user_info(current_username)
                if user_info and user_info['is_admin']:
                    delete_username = input("输入要删除的用户名: ").strip()
                    try:
                        delete_user(delete_username, user_info)
                        print("用户删除成功")
                    except PermissionError as e:
                        print(e)
                else:
                    print("只有管理员可以删除用户")
            else:
                print("认证失败")
        elif action == "info":
            username = input("输入用户名: ").strip()
            user_info = get_user_info(username)
            if user_info:
                print(f"用户信息: {user_info}")
            else:
                print("用户不存在")
        elif action == "quit":
            break
        else:
            print("无效操作")
