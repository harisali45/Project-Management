/**
 * Follow this link to configure rules http://codenarc.sourceforge.net/codenarc-configuring-rules.html
 */
ruleset {
    ruleset('rulesets/basic.xml')
    ruleset('rulesets/groovyism.xml')
    ruleset('rulesets/braces.xml')
    ruleset('rulesets/concurrency.xml')
    ruleset('rulesets/convention.xml')
    ruleset('rulesets/design.xml')
    //ruleset('rulesets/dry.xml')
    ruleset('rulesets/enhanced.xml')
    ruleset('rulesets/exceptions.xml')
    ruleset('rulesets/formatting.xml') {
        SpaceAroundMapEntryColon(characterAfterColonRegex: /\s/)
    }
    ruleset('rulesets/generic.xml')
    ruleset('rulesets/logging.xml')
    ruleset('rulesets/security.xml')
    ruleset('rulesets/serialization.xml')
    ruleset('rulesets/unnecessary.xml')
    ruleset('rulesets/unused.xml')
    ruleset('rulesets/imports.xml')
    ruleset('rulesets/naming.xml') {
        ClassName {
            regex = '^[A-Z][\\$a-zA-Z0-9]*$'
        }
        FieldName {
            regex = '^_?[a-z][a-zA-Z0-9]*$'
            finalRegex = '^_?[a-z][a-zA-Z0-9]*$'
            staticFinalRegex = '^logger$|^[A-Z][A-Z_0-9]*$|^serialVersionUID$'
        }
        MethodName {
            regex = '^[a-z][\\$_a-zA-Z0-9]*$|^.*\\s.*$'
        }
        VariableName {
            finalRegex = '^[a-z][a-zA-Z0-9]*$'
        }
    }
}
