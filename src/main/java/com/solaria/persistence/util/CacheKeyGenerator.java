package com.solaria.persistence.util;

public final class CacheKeyGenerator {
    private static final String PREFIX = "core"; 
    // como vai ser implementado tanto no chatbot quanto mobile, o certo é separar entre "core" e "agent".

    private CacheKeyGenerator() { // Construtor vazio pois ele não permite que seja criado nenhum objeto, apenas métodos
    }

    public static String supplierSearch(String filters) { // Pesquisa de fornecedores por meio de filtros
        return PREFIX + ":search:supplier:" + filters;
    }

    public static String searchCounter(String filters) {  // Contador de pesquisas, define o que é armazenado no Redis com base em popularidade
        return PREFIX + ":counter:search:" + filters;
    }
}
