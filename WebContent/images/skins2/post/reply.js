var Dvbbs_filterScript = true;
var Dvbbs_charset="gb2312";
var Dvbbs_bLoad=false;
var Dvbbs_bTextMode=1;
var Dvbbs_Mode=1;
var Dvbbs_bIsIE5=document.all;
var Dvbbs_Mode = 3;
if (Dvbbs_bIsIE5){
}
else{
	var Dvbbs_bIsNC=true;
}
function Dvbbs_InitDocument(hiddenid, charset)
{
	if (charset!=null){Dvbbs_charset=charset;}
	var Dvbbs_bodyTag="<style type=\"text/css\" type=\"text/css\">.quote{margin:5px 20px;border:1px solid #CCCCCC;padding:5px; background:#F3F3F3 }\nbody{boder:0px}.HtmlCode{margin:5px 20px;border:1px solid #CCCCCC;padding:5px;background:#FDFDDF;font-size:14px;font-family:Tahoma;font-style : oblique;line-height : normal ;font-weight:bold;}\nbody{boder:0px}</style></head><body bgcolor=\"#FFFFFF\" title=\"Ctrl+Enterֱ���ύ����\" >";
	var editor=IframeID;
	var h=document.getElementById(hiddenid).value;
	if (navigator.appVersion.indexOf("MSIE 6.0",0)==-1){
	editor.document.designMode="On";
	}
	editor.document.open();
	//editor.document.write ('<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">');
	editor.document.write ('<html><head>');
	if (Dvbbs_bIsIE5){
	editor.document.write ('<script language="javascript" type="text/javascript">');
	editor.document.write ('function imgresize(o){');
	editor.document.write ('if(o.width > 500 ){');
	editor.document.write ('	o.style.width=\'500px\';');
	editor.document.write ('}');
	editor.document.write ('if(o.height > 800){');
	editor.document.write ('	o.style.height=\'800px\';');
	editor.document.write ('}');
	editor.document.write ('}');
	editor.document.write ('<\/script>');
	}
	editor.document.write(Dvbbs_bodyTag);
	if (h!="")
	{
		editor.document.write(unescape(h));
	}
	editor.document.write ("</body>");
	editor.document.write ("</html>");
	editor.document.close();
	editor.document.body.contentEditable = "True";
	editor.document.charset=Dvbbs_charset;
	Dvbbs_bLoad=true;
	Dvbbs_setStyle();
	editor.document.onkeyup = function() { editor_event(hiddenid); };
}

function editor_event(objname){
	var ispost=0;
	copy_preview();
	 var editEvent = IframeID ? IframeID.event : event;
	 if (editEvent && editEvent.keyCode) {
		if(((editEvent.ctrlKey && editEvent.keyCode == 13) || (editEvent.altKey && editEvent.keyCode == 83)) && ispost==0){
			Dvbbs_CopyData(objname);
			document.Dvform.submit();
		}
	 }
}


function Dvbbs_setStyle()
{
	if (Dvbbs_Mode==2)
	{
		Dvbbs_bTextMode=3;
	}
	var bs = IframeID.document.body.style;
	bs.scrollbar3dLightColor= '#D4D0C8';
	bs.scrollbarArrowColor= '#000000';
	bs.scrollbarBaseColor= '#D4D0C8';
	bs.scrollbarDarkShadowColor= '#D4D0C8';
	bs.scrollbarFaceColor= '#D4D0C8';
	bs.scrollbarHighlightColor= '#808080';
	bs.scrollbarShadowColor= '#808080';
	bs.scrollbarTrackColor= '#D4D0C8';
	bs.border='1';
}
function Dvbbs_CopyData(hiddenid)
{
	var con=OSWEBXHTML.GetXHTML(IframeID.document.body);
	document.getElementById(hiddenid).value = con.replace(/��/gi,".");
}
//��ʽ��t��
function Dvbbs_correctUrl(cont)
{
	var regExp;
	regExp = "/<a([^>]*) href\s*=\s*([^\s|>]*)([^>]*)/gi";
	cont = cont.replace(regExp, "<a href=$2 target=\"_blank\" ");
	regExp = "/<a([^>]*)><\/a>/gi";
	cont = cont.replace(regExp, "");
	return cont;
}

//----------------
function Dvbbs_formatimg()
{
	var tmp ="";
	if (Dvbbs_bIsIE5){
		tmp=IframeID.document.body.all.tags("IMG");
	}else{
		tmp=IframeID.document.getElementsByTagName("IMG");
	}
	for(var i=0;i<tmp.length;i++){
		var tempstr='';
		if(tmp[i].align!=''){tempstr="align="+tmp[i].align;}
		if(tmp[i].border!=''){tempstr=tempstr+"border="+tmp[i].border;}
		tmp[i].outerHTML="<IMG src=\""+tmp[i].src+"\""+tempstr+">";
	}
}
//�������HTML����
function Dvbbs_cleanHtml(content)
{
	if(Dvbbs_bTextMode!=1){
		content = content.replace(/<img([^>]*) (src\s*=\s*([^\s|>])*)([^>]*)>/gi,"<img $2>");
	}
	content = content.replace(/<p>&nbsp;<\/p>/gi,"");
	content = content.replace(/<p><\/p>/gi,"<p>");
	content = content.replace(/<div><\/\1>/gi,"");
	content = content.replace(/(\r\n){10,}/gi,"$1");
	content = content.replace(/(<br>\s*){10,}/gi,"<br>");
	content = content.replace(/(<(meta|iframe|frame|span|tbody|layer)[^>]*>|<\/(iframe|frame|meta|span|tbody|layer)>)/gi, "");
	content = content.replace(/<\\?\?xml[^>]*>/gi, "");
	content = content.replace(/&nbsp;/gi, " ");
return content;
}

//������˼�JS��ȡ
function Dvbbs_FilterScript(content)
{
	content = content.replace(/<(\w[^div|>]*) class\s*=\s*([^>|\s]*)([^>]*)/gi,"<$1$3") ;
	content = content.replace(/<(\w[^font|>]*) style\s*=\s*([^>|\s]*)([^>]*)/gi,"<$1$3") ;
	content = content.replace(/<(\w[^>]*) lang\s*=\s*([^>|\s]*)([^>]*)/gi,"<$1$3") ;
	var RegExp = /<(script[^>]*)>((.|\n)*)<\/script>/gi;
	content = content.replace(RegExp, "[code]&lt;$1&gt;<br>$2<br>&lt;\/script&gt;[\/code]");
	RegExp = /<(\w[^>|\s]*)([^>]*)(on(finish|mouse|Exit|error|click|key|load|change|focus|blur))(.[^>]*)/gi;
	content = content.replace(RegExp, "<$1");
	RegExp = /<(\w[^>|\s]*)([^>]*)(&#|window\.|javascript:|js:|about:|file:|Document\.|vbs:|cookie| name| id)(.[^>]*)/gi;
	content = content.replace(RegExp, "<$1");
	return content;
}

function Dvbbs_CleanCode(){
	var editor=IframeID;
	editor.focus();
	checkword=1;
	editor.document.body.innerHTML= OSWEBXHTML.GetXHTML(editor.document.body);
	checkword=0;

}

var colour;
function FormatText(command, option)
{
//var codewrite;
if(Dvbbs_bTextMode==3&&command!="RemoveFormat"){
		switch (command)
		{
			case 'fontsize':
				Dv_ubb("size",option);
				break;
			case 'fontname':
				Dv_ubb("face",option);
				break;
			case 'bold':
				Dv_ubb("B");
				break;
			case 'italic':
				Dv_ubb("I");
				break;
			case 'underline':
				Dv_ubb("U");
				break;
		}
	return
}

if (Dvbbs_bIsIE5){
		if (option=="removeFormat"){
		command=option;
		option=null;}
	  	IframeID.document.execCommand(command, false, option);
		Dvbbs_pureText = false;
		IframeID.focus();
		
}else{
		if ((command == 'forecolor') || (command == 'backcolor')) {
			parent.command = command;
			buttonElement = document.getElementById(command);
			IframeID.focus();
			document.getElementById("colourPalette").style.left = getOffsetLeft(buttonElement) + "px";
			document.getElementById("colourPalette").style.top = (getOffsetTop(buttonElement) + buttonElement.offsetHeight) + "px";
		
			if (document.getElementById("colourPalette").style.visibility=="hidden")
				{document.getElementById("colourPalette").style.visibility="visible";
			}else {
				document.getElementById("colourPalette").style.visibility="hidden";
			}
			var sel = IframeID.document.selection; 
			if (sel != null) {
				colour = sel.createRange();
			}
		}
		else{
	  	IframeID.document.execCommand(command, false, option);
		Dvbbs_pureText = false;
		IframeID.focus();
		}
	}
}

function setColor(color)
{
	IframeID.document.execCommand(parent.command, false, color);
	IframeID.focus();
	document.getElementById("colourPalette").style.visibility="hidden";
}

function Dvbbs_doSelectClick(str, el)
{
	var Index = el.selectedIndex;
	if (Index != 0){
		el.selectedIndex = 0;
		FormatText(str,el.options[Index].value);
	}
}
function Dvbbs_validateMode()
{
	if (Dvbbs_bTextMode!=2) return true;
	alert("��ȡ��鿴HTMLԴ���롱ѡ����ʹ��ϵͳ�༭���ܻ����ύ!");
	IframeID.focus();
	return false;
}
function Dvbbs_foreColor()
{
	if (!Dvbbs_validateMode()) return;
	if (Dvbbs_bIsIE5){
		var arr = showModalDialog("images/post/selcolor.html", "", "dialogWidth:18.5em; dialogHeight:17.5em; status:0; help:0");
		if (arr != null){
			if(Dvbbs_bTextMode==3){
				Dv_ubb("color",arr);
			}else{
				FormatText('forecolor', arr);
			}
		}
		else{ IframeID.focus();}
	}else
		{
		FormatText('forecolor', '');
	}
}


function Dvbbs_backColor()
{
	if (!Dvbbs_validateMode()) return;
	if(Dvbbs_bTextMode==3){
		alert('��ǰ�༭��֧�ָ�UBB��ǡ�');
		return ;
	}
	if (Dvbbs_bIsIE5)
	{
		var arr = showModalDialog("images/post/selcolor.html", "", "dialogWidth:18.5em; dialogHeight:17.5em; status:0; help:0");
		if (arr != null) 
		{
			if(Dvbbs_bTextMode!=3){
			FormatText('backcolor', arr);
			}
		}
		else 
		{IframeID.focus();}
	}else
		{
		FormatText('backcolor', '');
		}
}
function Dvbbs_UserDialog(what)
{
	if (!Dvbbs_validateMode()) return;
	IframeID.focus();
	if(what == "InsertAlipay"){
		if (Dvbbs_bIsNC)
		{
		imagePath = prompt('����дͼƬt�ӵ�ַ��Ϣ��', 'http://');			
		if ((imagePath != null) && (imagePath != "")) {
			IframeID.document.execCommand('InsertImage', false, imagePath);
		}
		IframeID.document.body.innerHTML = (IframeID.document.body.innerHTML).replace("src=\"file://","src=\"");
		}else{
		Dvbbs_foralipay();
		}
	}
	Dvbbs_pureText = false;
	IframeID.focus();
}
function Dvbbs_foralipay()
{
	var arr = showModalDialog("images/post/alipay.htm", window, "dialogWidth:20em; dialogHeight:34em; status:0; help:0");
	if (arr)
	{
		IframeID.document.body.innerHTML+=arr;
	}
	IframeID.focus();
}
function ShowForum_Emot(thepage)
{
	var Emot_PageCount;
	var Emot_Count=Forum_Emot.length-2;
	if(Emot_Count%Emot_PageSize==0)
	{
		Emot_PageCount=(Emot_Count)/Emot_PageSize;
	}else{
		Emot_PageCount=Math.floor((Emot_Count)/Emot_PageSize)+1;
	}
	thepage=parseInt(thepage);
	if (thepage<=Emot_PageCount){
	var istr;
	var EmotStr='&nbsp;';
	var EmotPath=Forum_Emot[0];
	if (thepage!=1 && Emot_PageCount>1)
	{EmotStr+='<img style="cursor: pointer;" onClick="ShowForum_Emot('+(thepage-1)+');" src="Images/post/Previous.gif" width="14" height="14" title="��һҳ">&nbsp;';}
	for(var i=(thepage-1)*Emot_PageSize;i<(thepage-1)*Emot_PageSize+Emot_PageSize;i++)
	{
		if (i==Emot_Count){break;}
		if (i<9)
			{istr='em0'+(i+1);}
			else
			{istr='em'+(i+1);}
		EmotStr+='<img title="'+istr+'" style="cursor: pointer;padding:1px;" onload="if(this.width>30){this.width=30;Emot_PageSize=12;}" onClick=putEmot("'+istr+'"); src="'+EmotPath+Forum_Emot[i+1]+'">&nbsp;';
	}
	if (thepage!=Emot_PageCount)
	{EmotStr+='<img style="cursor: pointer;" onClick="Emot_PageSize=12;ShowForum_Emot('+(thepage+1)+');" src="Images/post/Next.gif" width="14" height="14" title="��һҳ">&nbsp;';}
	if (thepage<5 && Emot_PageCount>4)
	{EmotStr+='&nbsp;<img title="������鴰��" style="cursor: pointer;" onclick="ShadeDiv.ShowFrame(\'dispEmot.asp?action=disp&amp;&amp;sw=510&amp;sh=auto\');" src="'+EmotPath+Forum_Emot[50]+'" width="30" height="30" title="�±��� �����">';}
	//EmotStr+='��ҳ��<b>'+thepage+'</b>/<b>'+Emot_PageCount+'</b>����<b>'+(Emot_Count)+'</b>��';
	//EmotStr+="<select id=emotpage onchange=\"ShowForum_Emot(this.value);\">";
	//for (i=1; i<=Emot_PageCount;i++ )
	//{
	//	'EmotStr+="<option value=\""+i+"\">"+i;
	//}
	//EmotStr+="<\/select>";
	var Forum_EmotObj=document.getElementById("emot");
	Forum_EmotObj.innerHTML=EmotStr;
	//document.getElementById('emotpage').options[thepage-1].selected=true;
	}
}

function putEmot(thenNo)
{
	var ToAdd = '['+thenNo+']';
	IframeID.document.body.innerHTML+=ToAdd;
}
function copy_preview(){
	var dv_preview=document.getElementById("dv_preview");
	if (dv_preview && readCookie("preview")==1) {
		var html=OSWEBXHTML.GetXHTML(IframeID.document.body);
		dv_preview.innerHTML=html;
		if (html==''|| html=='<p>&nbsp;</p>' || html=='<p></p>') dv_preview.innerHTML='<font color="#cccccc">�ظ�Ч��Ԥ��</font> ';
		if (dv_preview.offsetHeight>200){
		dv_preview.style.overflow='auto';
		dv_preview.style.height='200px';
		}
}
}
function CheckCount(message,total)
{
Dvbbs_CopyData(message);
copy_preview();
var Getmessage=document.getElementById(message);
var Gettotal=document.getElementById(total);
	var max;
	max = Gettotal.value;
	if (Getmessage.value.length > max) {
	Getmessage.value = Getmessage.value.substring(0,max);
	alert("���ݲ��ܳ��� " + max + " ����!");
	document.Dvform.Submit.disabled=true;
	}
	else {
	document.Dvform.Submit.disabled=false;
	}
	
}

var ispost=0;
function ctlent(){
	if (event){
	if((event.ctrlKey && event.keyCode == 13) || (event.altKey && event.keyCode == 83))
		{
		ispost=1;
		Dvbbs_CopyData('Body');
		document.Dvform.submit();
	}
	}
}
function Gopreview(){
document.preview.theBody.value=IframeID.document.body.innerHTML; 
//var popupWin = window.open('', 'preview_page', 'scrollbars=yes,width=750,height=450');
window.open('', 'preview_page', 'scrollbars=yes,width=750,height=450');
document.preview.submit();
}
function Gopreview1(){
if (document.getElementById("dv_checkb1").checked){
		var dv_preview=document.getElementById("dv_preview");
		if(dv_preview){
		dv_preview.style.display='';
		createCookie("preview","1",365);
		}
	}else{
		var dv_preview=document.getElementById("dv_preview");
		if(dv_preview){
		dv_preview.style.display='none';
		createCookie("preview","",365);
		copy_preview();
		}
	}
}
function showtopic(){
	if (document.getElementById("dv_checkb2").checked){
		var dv_topic=document.getElementById("dv_topic");
		if(dv_topic){
		dv_topic.style.display='';
		createCookie("topic","1",365);
		}
	}else{
		var dv_topic=document.getElementById("dv_topic");
		if(dv_topic){
		dv_topic.style.display='none';
		createCookie("topic","",365);
		}
}
}
function showupload(){
	if (document.getElementById("dv_checkb3").checked){
		var dv_upload=document.getElementById("dv_upload");
		if(dv_upload){
		dv_upload.style.display='';
		createCookie("upload","1",365);
		}
	}else{
		var dv_upload=document.getElementById("dv_upload");
		if(dv_upload){
		dv_upload.style.display='none';
		createCookie("upload","",365);
		}
	}
}

function buildTemp(){
	if (readCookie("upload")=='1'){
			document.getElementById("dv_checkb3").checked = true;
			var dv_upload = document.getElementById("dv_upload");
			if(dv_upload){
			dv_upload.style.display='';
			}
		}else{
			document.getElementById("dv_checkb3").checked=false;
			var dv_upload=document.getElementById("dv_upload");
			if(dv_upload){
			dv_upload.style.display='none';
			}
	}
	if (readCookie("topic")=='1'){
			document.getElementById("dv_checkb2").checked=true;
			var dv_topic=document.getElementById("dv_topic");
			if(dv_topic){
			dv_topic.style.display='';
			}
		}else{
			document.getElementById("dv_checkb2").checked=false;
			var dv_topic=document.getElementById("dv_topic");
			if(dv_topic){
			dv_topic.style.display='none';
			}
		}
	if (readCookie("preview")=='1'){
			document.getElementById("dv_checkb1").checked=true;
			var dv_preview=document.getElementById("dv_preview");
			if(dv_preview){
			dv_preview.style.display='';
			}
		}else{
			document.getElementById("dv_checkb1").checked=false;
			var dv_preview=document.getElementById("dv_preview");
			if(dv_preview){
			dv_preview.style.display='none';
			}
		}

}