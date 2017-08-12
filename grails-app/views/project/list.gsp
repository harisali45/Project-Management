<html>
<head>
    <meta name="layout" content="main" />
    <title>Projects</title>
</head>
<body>
<div class="row clearfix">
    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
        <div class="card">
            <div class="header">
                <h2>
                Your Projects
                    <small>Click on a project to view details</small>
                </h2>
            </div>
            <div class="body">
                <ul class="list-group">
                    <g:each in="${projects}" var="project">
                    <li class="list-group-item">${project.title} <span class="badge bg-pink">0 new</span></li>
                    </g:each>
                </ul>
            </div>
        </div>
    </div>
</div>


</body>
</html>