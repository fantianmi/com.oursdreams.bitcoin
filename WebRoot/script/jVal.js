/*
 * jVal - dynamic jquery form field validation framework
 *	version 0.1.3
 * Author: Jim Palmer
 * Released under MIT license.
 */
(function($) {
	function showWarning (selectElement, message, autoHide, styleType) {
		$(selectElement).each(function(ind) {
				var par = $(this).parent().get(0);
				$(this).css({'margin-top':'','position':'','borderColor':'red'}).parent().find('.jfVal').stop().remove();
				$(par).find('.jValRelWrap').remove().end().append('<div class="jValRelWrap" style="display:none;"></div>').find('.jValRelWrap').append($(this).clone());
				var fieldWidth = $(par).find('.jValRelWrap').width(), fieldHeight = $(par).find('.jValRelWrap').height();
				$(par).find('.jValRelWrap').css({'width':fieldWidth,'height':fieldHeight}).empty();
				var paddedHeight = (fieldHeight + ($.fn.jVal.defaultPadding * 2));
				$(this).before(
					'<div class="jfVal' + ( styleType ? ' jfVal' + styleType : '' ) + '" style="left:' + ($(this).position().left - $.fn.jVal.defaultPadding - $.fn.jVal.defaultBorderWidth) + 'px; ' +
						'top:' + ($(this).position().top - $.fn.jVal.defaultPadding - $.fn.jVal.defaultBorderWidth + $.fn.jVal.IETopNudge) + 'px;">' +
						( (styleType == 'pod') ? '<div class="spacerBorder" style="height:' + paddedHeight + 'px;">' : '' ) + 
							'<div class="spacer' + ( styleType ? ' spacer' + styleType : '' ) + '" style="height:' + paddedHeight + 'px;"></div>' +
						( (styleType == 'pod') ? '</div>' : '' ) + 
						'<div class="icon' + ( styleType ? ' icon' + styleType : '' ) + '" style="height:' + paddedHeight + 'px;"><div class="iconbg"></div></div>' +
						'<div class="content' + ( styleType ? ' content' + styleType : '' ) + '" style="height:' + paddedHeight + 'px; line-height:' + paddedHeight + 'px;">' + message + '</div>' +
					'</div>');
				var spacerWidth = fieldWidth + ($.fn.jVal.defaultPadding * 2) + 8;
				$(par).find(styleType == 'pod' ? '.spacerBorder' : '.jfVal').css('padding', parseInt($.fn.jVal.defaultBorderWidth) + 'px').corner("round tr br 3px");
				$(par).find('.jfVal').width( spacerWidth + $(par).find('.icon').width() + $(par).find('.content').width() + $.fn.jVal.defaultPadding + $.fn.jVal.defaultBorderWidth);
				if ( autoHide ) {
					$(par).find('.spacer').width( spacerWidth ).animate({'opacity':0.95}, 2000).animate({'width':0}, 200);
					$(par).find('.jfVal').css({'opacity':0.93,'borderWidth':0}).animate({'borderWidth':0}, 2000).animate({'opacity':0}, 200, function() { $(this).remove(); });
					$(this).stop().animate({'opacity':0.95}, 2000, function() { $(this).css('borderColor', ''); });
				} else {
					$(par).find('.spacer').width( 0 ).animate({'width':spacerWidth}, 200);
					$(par).find('.jfVal').css('opacity', 0).animate({'opacity':0.95}, 400);
				}
				$(this).css(($.browser.msie) ? {'margin-top':1,'position':'absolute'} : {'position':'absolute'}).parent().find('.jValRelWrap').css('display', 'block');
			});
	};
	function valKey (keyRE, e, cF, cA) {
		if ( !(keyRE instanceof RegExp) ) return false;
		if ( /^13$/.test(String(e.keyCode || e.charCode)) ) {
			try { (this[cF]) ? this[cF](cA) : eval(cF); } catch(e) { return true; }
			return -1;
		} else if (	( typeof(e.keyCode) != 'undefined' && e.keyCode > 0 && keyRE.test(String.fromCharCode(e.keyCode)) ) || 
					( typeof(e.charCode) != 'undefined' && e.charCode > 0 && String.fromCharCode(e.charCode).search(keyRE) != (-1) ) ||
					( typeof(e.charCode) != 'undefined' && e.charCode != e.keyCode && typeof(e.keyCode) != 'undefined' && e.keyCode.toString().search(/^(8|9|45|46|35|36|37|39)$/) != (-1) ) ||
					( typeof(e.charCode) != 'undefined' && e.charCode == e.keyCode && typeof(e.keyCode) != 'undefined' && e.keyCode.toString().search(/^(8|9)$/) != (-1) ) ) {
			return 1;
		} else {
			return 0;
		}
	};
	$.fn.jVal = function () {
		$(this).stop().find('.jfVal').stop().remove();
		var passVal = true;
		$(this).find('[jVal]').css('borderColor', '').each( function () {
				try {
					eval( 'var cmd = ' + $(this).attr('jVal') + ';' );
					if ( cmd instanceof Object && ( cmd.valid instanceof RegExp && !cmd.valid.test($(this).val()) ) || ( cmd.valid instanceof Function && !cmd.valid($(this).val()) ) ) {
						showWarning(this, cmd.message || $.fn.jVal.defaultMessage, cmd.autoHide || false, cmd.styleType || 'pod');
						passVal = false;
					} else if ( ( cmd instanceof RegExp && !cmd.test($(this).val()) ) || ( cmd instanceof Function && !cmd($(this).val()) ) ) {
						showWarning(this, $.fn.jVal.defaultMessage);
						passVal = false;
					}
				} catch(e) { return true; }
			});
		return passVal;
	};
	$(document).ready( function () {
			$('input[jVal]').bind("blur", function (e) {
					$(this).parent().jVal();
				});
			$('input[jValKey]').bind("keypress", function (e) {
					try {
						eval( 'var cmd = ' + $(this).attr('jValKey') + ';' );
						var keyTest = valKey( ( (cmd instanceof Object) ? cmd.valid : cmd ), e, (cmd instanceof Object) ? cmd.cFunc : null, (cmd instanceof Object) ? cmd.cArgs : null );
						if ( keyTest == 0 ) {
							showWarning(this, (( cmd instanceof Object && cmd.message) || $.fn.jVal.defaultKeyMessage).replace('%c', String.fromCharCode(e.keyCode || e.charCode)), true, cmd.styleType || 'pod');
							return false;
						} else if ( keyTest == -1 ) return false;
						else $(this).css('borderColor', '').parent().find('.jfVal').remove();
						return true;
					} catch(e) { return true; }
				});
		});
	$.fn.jVal.defaultMessage = 'Invalid entry';
	$.fn.jVal.defaultKeyMessage = '"%c" Invalid character';
	$.fn.jVal.defaultPadding = 3;
	$.fn.jVal.defaultBorderWidth = 1;
	$.fn.jVal.IETopNudge = $.browser.msie ? -1 : 0;
})(jQuery);
