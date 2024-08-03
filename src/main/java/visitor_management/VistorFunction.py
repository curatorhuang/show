from flask import Flask, request, jsonify, abort

app = Flask(__name__)

# 模拟数据库
users = {}


# 获取用户信息
@app.route('/users/<user_id>', methods=['GET'])
def get_user(user_id):
    user = users.get(user_id)
    if not user:
        abort(404, description="User not found")
    return jsonify(user)


# 创建用户
@app.route('/users', methods=['POST'])
def create_user():
    data = request.get_json()
    if not data or not all(key in data for key in ('name', 'email', 'password')):
        abort(400, description="Invalid request parameters")

    user_id = str(len(users) + 1)
    if any(user['email'] == data['email'] for user in users.values()):
        abort(409, description="Email already registered")

    user = {
        "id": user_id,
        "name": data['name'],
        "email": data['email'],
        "created_at": "2024-01-01T00:00:00Z",
        "updated_at": "2024-01-01T00:00:00Z"
    }
    users[user_id] = user
    return jsonify(user), 201


# 错误处理
@app.errorhandler(404)
def not_found(error):
    return jsonify({"error": "Not found", "message": error.description}), 404


@app.errorhandler(400)
def bad_request(error):
    return jsonify({"error": "Bad request", "message": error.description}), 400


@app.errorhandler(401)
def unauthorized(error):
    return jsonify({"error": "Unauthorized", "message": error.description}), 401


@app.errorhandler(409)
def conflict(error):
    return jsonify({"error": "Conflict", "message": error.description}), 409


if __name__ == '__main__':
    app.run(debug=True)
