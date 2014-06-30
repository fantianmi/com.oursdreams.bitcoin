function showmsg()
{
	if (msg.readyState == 4)
	{
		if (msg.status == 200)
		{
			
			var showmsg = msg.responseText;
			window.alert(showmsg);
		}
	}
}


