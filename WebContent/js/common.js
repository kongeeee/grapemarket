var dim_list;
window.onload = function() {
    dim_list = document.getElementsByClassName("dim");
    
}
function popup(target) {
    for (var index = 0; index < dim_list.length; index++) {
        if (dim_list[index].classList[1] == target) {
            dim_list[index].style.display = "flex";
            fadeIn(dim_list[index])
        }
    }
}
function popup_close(element) {
	element.parentNode.parentNode.style.opacity = 0.0;
    element.parentNode.parentNode.style.display = "none";
    clearInterval(delivery);
}

function fadeIn(target) {
	var level = 0;
	var inTimer = null;
	inTimer = setInterval( function() {
		level = fadeInAction(target, level ,inTimer);
	}, 10);
}
function fadeInAction(target, level, inTimer) {
	level = level + 0.05;
	changeOpacity(target, level);
	if(level > 1) clearInterval(inTimer);
	return level;
}
function fadeOut(target) {
	var level = 1;
	var outTimer = null;
	outTimer = setInterval( function() {
		level = fadeOutAction(target, level, outTimer);
	}, 50);
}
function fadeOutAction(target, level, outTimer) {
	level = level - 0.05;
	changeOpacity(target, level);
	if(level < 0) {
		clearInterval(outTimer);
	}
	return level;
}
function changeOpacity(target, level) {
	var obj = target;
	obj.style.opacity = level;
	obj.style.MozOpacity = level;
	obj.style.khtmlOpacity = level;
	obj.style.MsFilter = "'progid: DXImageTransform.Microsoft.Alpha(Opacity=" + (level * 100) + ")'";
	obj.style.filter = "alpha(opacity=" + (level * 100) + ");";
}