<!DOCTYPE html>
<body>

<head>
    <meta charset="UTF-8">
    <title>Login</title>

    <meta name="layout" content="start" />

</head>

<body class="signup-page" />
<div class="signup-box">
    <div class="logo">
        <a href="javascript:void(0);">Project<b>Management</b></a>

    </div>
    <div class="card">
        <div class="body">
            <g:form id="sign_up" url="${g.createLink(controller: "user", action: "signUp")}">
                <div class="msg">Register</div>
                <div class="input-group">
                    <span class="input-group-addon">
                        <i class="material-icons">person</i>
                    </span>
                    <div class="form-line">
                        <input type="text" class="form-control" name="username" placeholder="Username" required autofocus>
                    </div>
                </div>
                <div class="input-group">
                    <span class="input-group-addon">
                        <i class="material-icons">person</i>
                    </span>
                    <div class="form-line">
                        <input type="text" class="form-control" name="name" placeholder="Name" required autofocus>
                    </div>
                </div>
                <div class="input-group">
                    <span class="input-group-addon">
                        <i class="material-icons">email</i>
                    </span>
                    <div class="form-line">
                        <input type="email" class="form-control" name="email" placeholder="Email Address" required>
                    </div>
                </div>
                <div class="input-group">
                    <span class="input-group-addon">
                        <i class="material-icons">lock</i>
                    </span>
                    <div class="form-line">
                        <input type="password" class="form-control" name="password" minlength="6" placeholder="Password" required>
                    </div>
                </div>
                <div class="input-group">
                    <span class="input-group-addon">
                        <i class="material-icons">lock</i>
                    </span>
                    <div class="form-line">
                        <input type="password" class="form-control" name="confirmPassword" minlength="6" placeholder="Confirm Password" required>
                    </div>
                </div>

                <button class="btn btn-block btn-lg bg-pink waves-effect" type="submit">SIGN UP</button>

                <div class="m-t-25 m-b--5 align-center">
                    <a href="/login">${g.message(code: "user.registered")}</a>
                </div>
            </g:form>
        </div>
    </div>
</div>

</body>
</html>
