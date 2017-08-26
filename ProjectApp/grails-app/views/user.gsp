<html>
<head>
    <meta name="layout" content="main" />
    <title>Profile</title>
</head>
<body>
<div class="row clearfix">
    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
        <div class="card">
            <div class="header">
                <h2>
                    User Details
                </h2>
            </div>
            <div class="body">
<g:form class="form-horizontal" url="${g.createLink(controller: 'user', action: 'save')}">
    <div class="row clearfix">
        <div class="col-lg-2 col-md-2 col-sm-4 col-xs-5 form-control-label">
            <label for="name">Name</label>
        </div>
        <div class="col-lg-10 col-md-10 col-sm-8 col-xs-7">
            <div class="form-group">
                <div class="form-line">
                    <input type="text" id="name"  name="name" class="form-control" placeholder="Enter Name"
                           value="${fieldValue(bean: user, field: 'name')}" required />
                </div>
            </div>
        </div>
    </div>
    <div class="row clearfix">
        <div class="col-lg-2 col-md-2 col-sm-4 col-xs-5 form-control-label">
            <label for="email">Email</label>
        </div>
        <div class="col-lg-10 col-md-10 col-sm-8 col-xs-7">
            <div class="form-group">
                <div class="form-line">
                    <input type="email" id="email"  name="email" class="form-control" placeholder="Enter Email"
                           value="${fieldValue(bean: user, field: 'email')}" required />
                </div>
            </div>
        </div>
    </div>
    <div class="row clearfix">
        <div class="col-lg-offset-2 col-md-offset-2 col-sm-offset-4 col-xs-offset-5">
            <input type="submit" class="btn btn-primary m-t-15 waves-effect" value="Update"></input>
        </div>
    </div>
</g:form>
    </div>
</div>
</div>
</div>

    <div class="row clearfix">
        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
            <div class="card">
                <div class="header">
                    <h2>
                        Forgot Password
                    </h2>
                </div>
                <div class="body">
<g:form class="form-horizontal" url="${g.createLink(controller: 'user', action: 'changePassword')}">
                    <div class="row clearfix">
                        <div class="col-lg-2 col-md-2 col-sm-4 col-xs-5 form-control-label">
                            <label for="password">Current Password</label>
                        </div>
                        <div class="col-lg-10 col-md-10 col-sm-8 col-xs-7">
                            <div class="form-group">
                                <div class="form-line">
                                    <input type="password" id="password"  name="password" class="form-control" placeholder="Enter Current Password"
                                           value="${fieldValue(bean: user, field: 'password')}" required />
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row clearfix">
                        <div class="col-lg-2 col-md-2 col-sm-4 col-xs-5 form-control-label">
                            <label for="newPassword">New Password</label>
                        </div>
                        <div class="col-lg-10 col-md-10 col-sm-8 col-xs-7">
                            <div class="form-group">
                                <div class="form-line">
                                    <input type="password" id="newPassword"  name="newPassword" class="form-control" placeholder="Enter New Password"
                                           value="${fieldValue(bean: user, field: 'password')}" required />
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row clearfix">
                        <div class="col-lg-2 col-md-2 col-sm-4 col-xs-5 form-control-label">
                            <label for="confirmPassword">Confirm Password</label>
                        </div>
                        <div class="col-lg-10 col-md-10 col-sm-8 col-xs-7">
                            <div class="form-group">
                                <div class="form-line">
                                    <input type="password" id="confirmPassword"  name="confirmPassword" class="form-control" placeholder="Confirm Password"
                                           value="${fieldValue(bean: user, field: 'confirmPassword')}" required />
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row clearfix">
                        <div class="col-lg-offset-2 col-md-offset-2 col-sm-offset-4 col-xs-offset-5">
                            <input type="submit" class="btn btn-primary m-t-15 waves-effect" value="Change Password"></input>
                        </div>
                    </div>
</g:form>
                </div>
            </div>
        </div>
    </div>

    <div class="row clearfix">
        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
            <div class="card">
                <div class="header">
                    <h2>
                        Invite User
                    </h2>
                </div>
                <div class="body">
                    <g:form url="${g.createLink(controller: "user", action: "invite")}" >
                        <div class="row clearfix">
                            <div class="col-lg-2 col-md-2 col-sm-4 col-xs-5 form-control-label">
                                <label for="newUserName">New User Name</label>
                            </div>
                            <div class="col-lg-10 col-md-10 col-sm-8 col-xs-7">
                                <div class="form-group">
                                    <div class="form-line">
                                        <input type="text" id="newUserName"  name="newUserName" class="form-control" placeholder="New User Name"
                                                required />
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row clearfix">
                            <div class="col-lg-2 col-md-2 col-sm-4 col-xs-5 form-control-label">
                                <label for="newUserEmail">New User Email</label>
                            </div>
                            <div class="col-lg-10 col-md-10 col-sm-8 col-xs-7">
                                <div class="form-group">
                                    <div class="form-line">
                                        <input type="text" id="newUserEmail"  name="newUserEmail" class="form-control" placeholder="New User Email"
                                                required />
                                    </div>
                                </div>
                            </div>
                        </div>
                    <div class="row clearfix">
                        <div class="col-lg-offset-2 col-md-offset-2 col-sm-offset-4 col-xs-offset-5">
                            <input type="submit" class="btn btn-primary m-t-15 waves-effect" value="Invite"></input>
                        </div>
                    </div>
                    </g:form>
                </div>
            </div>
        </div>
    </div>
    </body>