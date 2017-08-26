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
                ${project.title}
                    <small>Tasks in this project
                    <a href="${g.createLink(controller: "task", action: "edit", params: [projectId: project.id])}" class="btn bg-blue btn-circle-lg waves-effect waves-circle waves-float top-right"
                       data-toggle="tooltip" data-placement="bottom" title data-original-title="New Task" >
                        <i class="material-icons">add</i>
                    </a>
                </small>
                </h2>
            </div>
            <div class="body">
                <ul class="list-group">
                    <g:each in="${tasks}" var="task">
                    <li class="list-group-item">${task.title}
                        <div class="float-right keep-up">
                        <a class="btn btn-primary" href="${g.createLink(controller:"task", action: "edit",params: [taskId:task.id])}">View Details</a>
                        </div>
                    </g:each>
                </ul>
            </div>
        </div>
    </div>
</div>


</body>
</html>