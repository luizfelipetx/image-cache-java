package com.tx.porygon.enums;

public enum TypeRequest 
{	 
	 CREATE_BAR_CODE("barcode") , REQUEST_IMAGE("image"), CREATE_BARCODE_MATRIX("matrix"),CREATE_LABEL("etiqueta");
	 
	 public String type;
	 TypeRequest(String type){
		 this.type = type;
	 }
	 
	 public static TypeRequest getTypeRequest(String type){
		 
		 for (TypeRequest tp  : TypeRequest.values()) {
			if(tp.type.equals(type)){
				return tp;
			}
		} 
		 return TypeRequest.REQUEST_IMAGE;
	 }
}
