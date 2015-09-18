package org.mesis.bila

public enum NodeType {
	
    ROOT('Root'), PATRIMONIALE('Patrimoniale'), ECONOMICO('Economico'), ATTIVO("Attivo"), PASSIVO("Passivo"), COSTI("Costi"), RICAVI("Ricavi")
    
    final String value

    NodeType(String value) {
        this.value = value
    }
	
	String toString() { value }
	String getKey() { name() }
}
