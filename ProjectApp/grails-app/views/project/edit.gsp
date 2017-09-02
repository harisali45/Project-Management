<html>
<head>
    <meta name="layout" content="main" />
    <title>Projects</title>
</head>
<body>
<breadcrumb:list icon="edit" title="${project.id?'Edit Project':'New Project'}" ></breadcrumb:list>
<div class="row clearfix">
    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
        <div class="card">
            <div class="header">
                <h2>
                    <g:if test="${project.id}">
                    ${project.title}
                    <small>Created on <g:formatDate date="${project.created}" format="EEE, d MMM yyyy" /> by ${project.owner.name}</small>
                    </g:if>
                    <g:if test="${!project.id}">
<g:message code="new.message" args="['Project']" />
                        <small>Enter the details below and click on save</small>
                    </g:if>
                </h2>
            </div>
            <div class="body">
                <g:form class="form-horizontal" url="${g.createLink(controller: 'project', action: 'save')}">
                    <input type="hidden" name="id" value="${fieldValue(bean: project, field: 'id')}" />
                    <div class="row clearfix">
                        <div class="col-lg-2 col-md-2 col-sm-4 col-xs-5 form-control-label">
                            <label for="title">Title</label>
                        </div>
                        <div class="col-lg-10 col-md-10 col-sm-8 col-xs-7">
                            <div class="form-group">
                                <div class="form-line">
                                    <input type="text" id="title"  name="title" class="form-control" placeholder="Enter project title"
                                           value="${fieldValue(bean: project, field: 'title')}" required />
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row clearfix">
                        <div class="col-lg-2 col-md-2 col-sm-4 col-xs-5 form-control-label">
                            <label for="description">Description</label>
                        </div>
                        <div class="col-lg-10 col-md-10 col-sm-8 col-xs-7">
                            <div class="form-group">
                                <div class="form-line">
                                    <g:textArea name="description" value="${fieldValue(bean: project, field: 'description')}"
                                    class="form-control no-resize" placeholder="Enter description" />
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="row clearfix">
                        <div class="col-lg-offset-2 col-md-offset-2 col-sm-offset-4 col-xs-offset-5">
                            <button type="submit" class="btn btn-primary m-t-15 waves-effect" >
                                <i class="material-icons">edit</i>
                                <span>Update</span>
                            </button>
                        </div>
                    </div>
                </g:form>
            </div>
        </div>
    </div>
</div>

<g:if test="${project.id}">
<div class="row clearfix">
    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
        <div class="card">
            <div class="header">
                <h2>
                    Contributers
                </h2>
            </div>
            <div class="body">
                <div class="row clearfix">
                    <div class="col-lg-2 col-md-2 col-sm-4 col-xs-5 form-control-label">
                        <label >Current Users</label>
                    </div>
                    <div class="col-lg-10 col-md-10 col-sm-8 col-xs-7">
                        <div class="form-group">
                                <g:each in="${usersInProject}" var="user">
                                    <g:if test="${user.id == project.owner.id}">
                                        <span class="custom-tag tag label label-success margin-all">${user.name}
                                        </span>
                                    </g:if>
                                    <g:if test="${user.id != project.owner.id}">
                                    <span class="custom-tag tag label label-info margin-all">${user.name}
                                    <g:link action="removeUser" params="[userId: user.id, projectId: project.id]"
                                            onclick="" ><span>&times;</span></g:link></span>
                                    </g:if>
                                </g:each>
                        </div>
                    </div>
                </div>
                <g:form class="form-horizontal" url="${g.createLink(controller: 'project', action: 'addUser')}">
                    <input type="hidden" name="projectId" value="${fieldValue(bean: project, field: 'id')}" />
                <div class="row clearfix">
                    <div class="col-lg-2 col-md-2 col-sm-4 col-xs-5 form-control-label">
                        <label for="newUser">Add User</label>
                    </div>
                    <div class="col-lg-10 col-md-10 col-sm-8 col-xs-7">
                        <div class="form-group">
                            <div class="form-line">
                            <g:select id="newUser" name="newUser"
                                      from="${usersNotInProject}"
                                      optionKey="id"
                                      optionValue="name"
                                      data-live-search="true"
                                      class="selectpicker"
                                        required="required"
                                      noSelection="['':'--------Add User --------']"
                            />
                            </div>
                        </div>
                    </div>
                        </div>
                    <div class="row clearfix">
                        <div class="col-lg-offset-2 col-md-offset-2 col-sm-offset-4 col-xs-offset-5">
                            <button type="submit" class="btn btn-primary m-t-15 waves-effect" >
                            <i class="material-icons">edit</i>
                            <span>Update</span>
                        </button>
                        </div>
                    </div>
                </g:form>
                    </div>
                </div>
            </div>
        </div>
</g:if>

</body>
</html>