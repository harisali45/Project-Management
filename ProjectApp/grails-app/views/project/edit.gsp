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
                    <small>Project Details</small>
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
                                           value="${fieldValue(bean: project, field: 'title')}" />
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
                                    <textarea type="text" id="description" name="description" class="form-control no-resize" placeholder="Enter project description"
                                              value="${fieldValue(bean: project, field: 'description')}" rows="3" cols="20"></textarea>
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
</body>
</html>