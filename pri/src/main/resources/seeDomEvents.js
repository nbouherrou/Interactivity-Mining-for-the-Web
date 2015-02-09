window.seeDomEvents = function() {

	var protocol = window.location.protocol === 'file:' ? 'http:' : '';
	var url = protocol+'//www.sprymedia.co.uk/VisualEvent/VisualEvent_Loader.js';
	
	if( typeof VisualEvent!='undefined' ) {
		if ( VisualEvent.instance !== null ) {
			VisualEvent.close();
		}else {
			window.a = new VisualEvent();
			//window.masterNode.s
			
			console.log("master element : ");
			
			console.log(window.a.s);
			
			console.log("step 01");
			
			var objects_array = new Array();
			
			console.log("elements length : " + window.a.s.elements.length);
			
			for (var i = 0 ; i < window.a.s.elements.length ; i++){
				
				console.log("in the loop #" + i);
					
				// ------------ objects strategy  --------------- //
					
					var single_node = {};
					
					single_node.node 	   = "none";
				
					
					// We Add the tag name
					if (typeof window.a.s.elements[i].node.nodeName !== 'undefined') {
						
						console.log("NAME FOUND");
					    
						single_node.node 	   =  window.a.s.elements[i].node.nodeName.toLowerCase().replace('#', '');
						
					}
					
					
					// We Add the class tag
					if (typeof window.a.s.elements[i].node.id !== 'undefined' &&  window.a.s.elements[i].node.id !== null && window.a.s.elements[i].node.id !== '') {
					    
						console.log("ID FOUND");
						
						single_node.node 	   += ' #'+$(window.a.s.elements[i].node).attr('id').split(' ').join(' #');
						
					}
					
					
					// We Add the class tag
					if (typeof window.a.s.elements[i].node.className !== 'undefined' &&  window.a.s.elements[i].node.className !== null && window.a.s.elements[i].node.className !== '') {
					    
						console.log("CLASS FOUND");
						
						single_node.node 	   += ' .'+$(window.a.s.elements[i].node).attr('class').split(' ').join(' .');
						
					}
					
					
					single_node.listeners_nb 	= window.a.s.elements[i].listeners.length;
	
					var events = new Array();
					
					console.log("event length " + window.a.s.elements[i].listeners.length);
					
					for (var j = 0 ; j < single_node.listeners_nb ; j++){
						
						var event = {};
					
						event.func   = (window.a.s.elements[i].listeners[j].func);
						
						event.source = (window.a.s.elements[i].listeners[j].source);
						
						event.type   = (window.a.s.elements[i].listeners[j].type);
						
						events.push(event);
					
					}
						
					single_node["events"] = events;
					
					objects_array.push(single_node);
									
				}
				
				console.log("<--- allNodes --->");
				
				var allNodes = {};
				
				allNodes["nodes"]  = (objects_array);
				
				console.log(allNodes);
				
				return JSON.stringify(allNodes);

		}
	}else {
		
		var n=document.createElement('script');
		
		n.setAttribute('language','JavaScript');
		
		n.setAttribute(	'src',	url+ '?rand=' + new Date().getTime());
		
		document.body.appendChild(n);
	}
}

seeDomEvents();