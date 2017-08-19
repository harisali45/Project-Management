<!DOCTYPE html>
<body>

<head>
    <meta charset="UTF-8">
    <title>Login</title>

    <meta name="layout" content="start" />

</head>

<body class="fp-page" />

    <div class="fp-box">
        <div class="logo">
            <a href="javascript:void(0);">Project<b>Management</b></a>

        </div>
        <div class="card">
            <div class="body">
                <form id="forgot_password" method="POST">
                    <div class="msg">
                        Enter your email address that you used to register. We'll send you an email with your username and a
                        link to reset your password.
                    </div>
                    <div class="input-group">
                        <span class="input-group-addon">
                            <i class="material-icons">email</i>
                        </span>
                        <div class="form-line">
                            <input type="email" class="form-control" name="email" placeholder="Email" required autofocus>
                        </div>
                    </div>

                    <button class="btn btn-block btn-lg bg-pink waves-effect" type="submit">RESET MY PASSWORD</button>

                    <div class="row m-t-20 m-b--5 align-center">
                        <a href="/login">Sign In!</a>
                    </div>
                </form>
            </div>
        </div>
    </div>

</body>
</html>
