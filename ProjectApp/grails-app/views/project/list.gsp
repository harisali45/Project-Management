<html>
<head>
    <meta name="layout" content="main" />
    <title>Projects</title>
</head>
<body>
<breadcrumb:list icon="list" title="Projects" ></breadcrumb:list>
<div class="row clearfix">
    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
        <div class="card">
            <div class="header">
                <h2>
                Your Projects
                    <small>Click on a project to view details
                    <a href="${g.createLink(controller: "project", action: "edit")}" class="btn bg-blue btn-circle-lg waves-effect waves-circle waves-float top-right"
                data-toggle="tooltip" data-placement="bottom" title data-original-title="New Project" >
                        <i class="material-icons">add</i>
                    </a>
                </small>
                </h2>
            </div>
            <div class="body">
                <ul class="list-group">
                    <g:each in="${projects}" var="project">
                    <li class="list-group-item">${project.title}
                        <div class="float-right keep-up btn-group">
                        <a class="btn btn-xs bg-blue waves-effect " href="${g.createLink(controller:"task", action: "list",params: [projectId:project.id])}">
                            <i class="material-icons">playlist_add_check</i><span>Tasks</span></a>
                        <a class="btn btn-xs bg-blue waves-effect " href="${g.createLink(controller: 'project', action: 'edit', params: [projectId: project.id])}">
                            <i class="material-icons">format_list_bulleted</i><span>Details</span></a>
                    </div>
                    </li>
                    </g:each>
                </ul>
            </div>
        </div>
    </div>
</div>


</body>
</html>