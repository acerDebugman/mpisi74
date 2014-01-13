<%@ page language="java" pageEncoding="UTF-8"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>jsTree &raquo; Demo </title>
<link type="text/css" rel="stylesheet" href="tree_component.css"/>

<script type="text/javascript" src="_lib.js"></script>
<script type="text/javascript" src="tree_component.js"></script>

</head>
<body>

<h2>Predefined JSON</h2>
<div id="demo" class="demo" style="height:200px;"></div>

<script type="text/javascript">
conf = {
	data  : {
		type  : "json",
		json  : [ 
			{ attributes: { id : "pjson4_1" }, data: "Root node 1", children : [
				{ attributes: { id : "pjson4_2" }, data: { title : "Custom icon" } },
				{ attributes: { id : "pjson4_3" }, data: "Child node 2" },
				{ attributes: { id : "pjson4_4" }, data: "Some other child node" }
			]}, 
			{ attributes: { id : "pjson4_5" }, data: "Root node 2" } 
		]
	},
	ui : {
		theme_name : "checkbox"
	},
	callback : { 
		onchange : function (NODE, TREE_OBJ) {
			if(TREE_OBJ.settings.ui.theme_name == "checkbox") {
				var $this = $(NODE).is("li") ? $(NODE) : $(NODE).parent();
				if($this.children("a.unchecked").size() == 0) {
					TREE_OBJ.container.find("a").addClass("unchecked");
				}
				$this.children("a").removeClass("clicked");
				if($this.children("a").hasClass("checked")) {
					$this.find("li").andSelf().children("a").removeClass("checked").removeClass("undetermined").addClass("unchecked");
					var state = 0;
				}
				else {
					$this.find("li").andSelf().children("a").removeClass("unchecked").removeClass("undetermined").addClass("checked");
					var state = 1;
				}
				$this.parents("li").each(function () { 
					if(state == 1) {
						if($(this).find("a.unchecked, a.undetermined").size() - 1 > 0) {
							$(this).parents("li").andSelf().children("a").removeClass("unchecked").removeClass("checked").addClass("undetermined");
							return false;
						}
						else $(this).children("a").removeClass("unchecked").removeClass("undetermined").addClass("checked");
					}
					else {
						if($(this).find("a.checked, a.undetermined").size() - 1 > 0) {
							$(this).parents("li").andSelf().children("a").removeClass("unchecked").removeClass("checked").addClass("undetermined");
							return false;
						}
						else $(this).children("a").removeClass("checked").removeClass("undetermined").addClass("unchecked");
					}
				});
			}
		}
	}
};
function change_theme(new_theme) {
	conf.ui.theme_name = new_theme;
	tree1.destroy();
	tree1.init($("#demo"), $.extend({},conf));
};
$(function () {
    tree1 = $.tree_create();
    tree1.init($("#demo"), $.extend({},conf));
});
</script>

</body>
</html>