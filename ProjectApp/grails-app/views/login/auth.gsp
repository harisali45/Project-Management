<!DOCTYPE html>
<body>

<head>
    <meta charset="UTF-8">
    <title>Login</title>

    <meta name="layout" content="start" />

</head>

<body class="login-page" />
<div class="login-box">
    <div class="logo">
        <a href="javascript:void(0);">Project<b>Management</b></a>

    </div>
    <div class="card">
        <div class="body">
           %{-- <g:form id="sign_in" method="POST" url="${g.createLink(controller: "login", action: "login")}">--}%
                <form  method="POST" action="authenticate">
            <div class="msg">Sign in</div>
                <div class="input-group">
                    <span class="input-group-addon">
                        <i class="material-icons">person</i>
                    </span>
                    <div class="form-line">
                        <input type="text" class="form-control" name="username" value="${loginCommand?.username}"
                               placeholder="Username" required autofocus>
                    </div>
                </div>
                <div class="input-group">
                    <span class="input-group-addon">
                        <i class="material-icons">lock</i>
                    </span>
                    <div class="form-line">
                        <input type="password" class="form-control" name="password" value="${loginCommand?.password}"
                               placeholder="Password" required>
                    </div>
                </div>
                <div class="row">

                    <div class="col-xs-offset-8 col-xs-4">
                        <input class="btn btn-block bg-pink waves-effect" type="submit" value="SIGN IN"></input>
                    </div>
                </div>
                <g:if test="${flash.message}">
                <div class="row">
                    <div class="col-md-12 error-msg">
                        ${flash.message}
                    </div>
                </div>
                </g:if>
                <div class="row m-t-15 m-b--20">
                    <div class="col-xs-6">
                        <a href="${g.createLink(controller: "user", action: "showSignUp")}">Register Now!</a>
                    </div>
                    <div class="col-xs-6 align-right">
                        <a href="${g.createLink(controller: "access", action: "showForgotPassword")}">Forgot Password?</a>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>

<asset:javascript src="pages/sign-in.js" />

</body>
</html>
