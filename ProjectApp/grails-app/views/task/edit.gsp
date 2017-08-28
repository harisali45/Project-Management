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
                    <g:if test="${task.id}">
                        ${task.title}
                        <small>Created on <g:formatDate date="${task.created}" format="EEE, d MMM yyyy" /> by</small>
                    </g:if>
                    <g:if test="${!task.id}">
                        <g:message code="new.message" args="['Task']" />
                        <small>Enter the details below and click on save</small>
                    </g:if>
                </h2>
            </div>
            <div class="body">
                <ul class="nav nav-tabs tab-nav-right" role="tablist">
                    <li role="presentation" class="active"><a href="#edit" data-toggle="tab" aria-expanded="true">
                        <i class="material-icons">edit</i>
                        Details</a></li>
                    <li role="presentation" class=""><a href="#comments" data-toggle="tab" aria-expanded="false">
                        <i class="material-icons">comment</i>
                        Comments</a></li>
                </ul>
                <div class="tab-content">
                    <div role="tabpanel" class="tab-pane fade active in" id="edit">
                        <g:form class="form-horizontal" url="${g.createLink(controller: 'task', action: 'save')}">
                            <input type="hidden" name="id" value="${fieldValue(bean: task, field: 'id')}" />
                            <input type="hidden" name="project" value="${fieldValue(bean: task, field: 'project')}" />
                            <div class="row clearfix">
                                <div class="col-lg-2 col-md-2 col-sm-4 col-xs-5 form-control-label">
                                    <label for="title">Title</label>
                                </div>
                                <div class="col-lg-10 col-md-10 col-sm-8 col-xs-7">
                                    <div class="form-group">
                                        <div class="form-line">
                                            <input type="text" id="title"  name="title" class="form-control" placeholder="Enter project title"
                                                   value="${fieldValue(bean: task, field: 'title')}" required />
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
                                            <g:textArea name="description" value="${fieldValue(bean: task, field: 'description')}"
                                                        class="form-control no-resize" />
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row clearfix">
                                <div class="col-lg-offset-2 col-md-offset-2 col-sm-offset-4 col-xs-offset-5">
                                    <input type="submit" class="btn btn-primary m-t-15 waves-effect" value="Update"></input>
                                </div>
                            </div>
                            <g:if test="${flash.message}">
                                <div class="row">
                                    <div class="col-md-12 error-msg">
                                        ${flash.message}
                                    </div>
                                </div>
                            </g:if>
                        </g:form>
                    </div>
                    <div role="tabpanel" class="tab-pane fade" id="comments">
                        <g:if test="${task.id}">
                        <g:each in="${task.comment}" var="comment">
                            <div class="col-lg-2 col-md-2 col-sm-12">
                                <i class="material-icons md-48 pull-right">account_circle</i>
                            </div>
                            <div class="col-lg-10 col-md-10 col-sm-12 col-xs-12" >
                            %{--<div class="col-lg-offset-2 col-md-offset-2 col-lg-10 col-md-10 col-sm-12 col-xs-12" >--}%
                                <blockquote class="blockquote-small">
                                    <p>
                                    ${comment.content}
                                    </p>
                                    <footer>
                                        by Haris on 25 May 2017
                                    </footer>
                                </blockquote>
                            </div>
                            <div class="col-sm-12 div-divider" ></div>
                        </g:each>
                        <g:form class="form-horizontal" url="${g.createLink(controller: 'task', action: 'postComment')}">
                            <g:hiddenField name="taskId" value="${g.fieldValue(bean: task, field: 'id')}" />
                            <div class="row clearfix">
                                <div class="col-lg-offset-2 col-md-offset-2 col-sm-offset-4  col-lg-10 col-md-10 col-sm-8 col-xs-7">
                                    <div class="form-group">
                                        <div class="form-line">
                                            <g:textArea name="content" class="form-control no-resize" required="required" placeholder="Type new comment here"
                                                        value="${fieldValue(bean: comment, field: 'content')}" />

                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row clearfix">
                                <div class="col-lg-offset-2 col-md-offset-2 col-sm-offset-4 col-xs-offset-5">
                                    <button type="submit" class="btn btn-primary m-t-15 waves-effect" >
                                        <i class="material-icons">comment</i>
                                        <span>Post Comment</span>
                                    </button>
                                </div>
                            </div>
                            <g:if test="${flash.message}">
                                <div class="row">
                                    <div class="col-md-12 error-msg">
                                        ${flash.message}
                                    </div>
                                </div>
                            </g:if>
                        </g:form>
                        </g:if>
                        <g:if test="${!task.id}">
                            Create a task first to post comments
                        </g:if>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>