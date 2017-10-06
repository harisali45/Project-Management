<html>
<head>
    <meta name="layout" content="main" />
    <title>Projects</title>
</head>
<body>
<breadcrumb:list icon="list" title="Tasks" ></breadcrumb:list>
<div class="row clearfix">
    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
        <div class="card">
            <div class="header">
                <h2>
                    <g:form controller="task" action="list" >
                    Tasks in  <g:select id="newUser" name="projectId"
                                        from="${projects}"
                                        optionKey="id"
                                        optionValue="title"
                                        value="${project.id}"
                                        data-live-search="true"
                                        class="selectpicker"
                                        required="required"
                                        onchange="submit()"    />
                    </g:form>
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
                    <li class="list-group-item ">${task.title}
                        <div class="float-right keep-up">
                            <g:if test="${ task.deadline && task.deadline < new Date() }">
                                <a class="btn btn-danger btn-xs" href="">
                                    <i class="material-icons">error_outline</i>
                                </a>
                            </g:if>
                        <a class="btn btn-primary btn-xs" href="${g.createLink(controller:"task", action: "edit",params: [taskId:task.id])}">
                            <i class="material-icons">format_list_bulleted</i><span>Details</span>
                        </a>
                        </div>
                    </g:each>
                </ul>
            </div>
        </div>
    </div>
</div>


</body>
</html>