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
                    <small>Click on a project to view details
                    <a href="#!" class="btn bg-blue btn-circle-lg waves-effect waves-circle waves-float top-right"
                data-toggle="tooltip" data-placement="bottom" title data-original-title="New Project" >
                        <i class="material-icons">add</i>
                    </a>
                   %{-- <button type="button" class="btn btn-primary btn-block waves-effect" data-toggle="tooltip"
                    data-placement="bottom" title data-original-title="New Project">
                    <i class="material-icons">add</i>
                </button>--}%
                </small>
                    %{--<a class="btn btn-primary btn-circle float-right margin-all waves-effect" href=""><i class="material-icons">add</i> </a>--}%
                </h2>
            </div>
            <div class="body">
                <ul class="list-group">
                    <g:each in="${projects}" var="project">
                    <li class="list-group-item">${project.title}
                        <div class="float-right keep-up">
                        <a class="btn btn-sm bg-blue waves-effect " href="${g.createLink(controller:"task", action: "list",params: [projectId:project.id])}">View Tasks</a>
                        <a class="btn btn-sm bg-orange waves-effect " href="">View Details</a>
                    </div>
                    %{--<span class="badge bg-pink">0 new</span>--}%</li>
                    </g:each>
                </ul>
            </div>
        </div>
    </div>
</div>


</body>
</html>