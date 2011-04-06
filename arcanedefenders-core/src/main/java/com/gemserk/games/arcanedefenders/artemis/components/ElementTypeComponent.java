package com.gemserk.games.arcanedefenders.artemis.components;

import com.artemis.Component;
import com.gemserk.componentsengine.properties.Property;
import com.gemserk.games.arcanedefenders.ElementType;

public class ElementTypeComponent extends Component {
	
	private final Property<ElementType> elementType;
	
	public ElementType getElementType() {
		return elementType.get();
	}
	
	public void setElementType(ElementType elementType){
		this.elementType.set(elementType);
	}
	
	public ElementTypeComponent(Property<ElementType> elementType) {
		this.elementType = elementType;
	}

}
