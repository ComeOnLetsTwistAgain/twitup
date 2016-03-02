package com.iup.tp.twitup.document;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class MaxLengthTextDocument extends PlainDocument {
	//Store maximum characters permitted
	private int maxChars;

	@Override
	public void insertString(int offs, String str, AttributeSet a)
			throws BadLocationException {
		if(str != null && (getLength() + str.length() < maxChars)){
			super.insertString(offs, str, a);
		}
	}

	public void setMaxChars(int i) {
		// TODO Auto-generated method stub
		maxChars = i;		
	}

}