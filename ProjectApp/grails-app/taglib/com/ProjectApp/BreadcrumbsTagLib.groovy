package com.ProjectApp

class BreadcrumbsTagLib {
    //static defaultEncodeAs = [taglib:'html']
    //static encodeAsForTags = [tagName: [taglib:'html'], otherTagName: [taglib:'none']]

    static namespace = 'breadcrumb'

    def list = { attrs ->
        String queryString = request.queryString?"?${request.queryString}":""
        BreadcrumbUrl breadcrumb = new BreadcrumbUrl(title: "${attrs.title}" ,
                url: "${request.getRequestURL()}${queryString}", icon: "${attrs.icon}")
        Stack<BreadcrumbUrl> urls = session.getAt("urlStack")
        if(!urls) {
            urls = new Stack<BreadcrumbUrl>()
            session.putAt("urlStack", urls)
        } else {
            if(breadcrumb.isRootUrl()) {
                urls.clear()
            } else {
                def breadcrumbExists = urls.toList().find() { it.title == breadcrumb.title }
                if (breadcrumbExists) {
                    BreadcrumbUrl popped
                    while (breadcrumb != popped) {
                        popped = urls.pop()
                        log.info "popping ${popped.title}"
                        log.info "is equal ${popped == breadcrumb}"
                    }
                }
            }
        }
        log.info "found: ${urls.toList().contains(breadcrumb)}"
        urls.push(breadcrumb)

        //render breadcrumb
        String htmlString = "<div class='row clearfix'><ol class='breadcrumb'>"
        Iterator it = urls.iterator()
        while(it.hasNext()) {
            BreadcrumbUrl b = it.next()
            htmlString = """
                            ${htmlString}
                            <li>
                            <a href='${b.url}'>
                            <i class='material-icons'>${b.icon}</i>
                            ${b.title}
                            </a>
                            </li>
                         """

        }
        htmlString = "${htmlString}</ol></div>"
        out << htmlString
    }

}
