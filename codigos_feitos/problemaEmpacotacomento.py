import random
import numpy as np

def criar_matriz(tamanho):
    #criando a lista e populando com os valores de acordo com o tamanho
    '''
    FAZER: deixar popular entre 0 a n*n
    '''
    lista_inicial = list(range(0,tamanho*tamanho))
    #embaralha a lista
    random.shuffle(lista_inicial)
    #transformar em matriz
    matriz = np.reshape(lista_inicial,(tamanho,tamanho))
    return matriz

def converter_string(matriz):
    #inserindo em uma string todos os valores da matriz
    novoEstado = ""
    for i in range(len(matriz)):
        for j in range(len(matriz)):
            novoEstado += str(matriz[i][j])
    return novoEstado    

def inserir_visitados(lista_visitados, matriz,novoEstado):
    if novoEstado not in lista_visitados:
        lista_visitados.append(novoEstado)
    else:
        print("Este item já foi inserido!")
    return lista_visitados

if __name__ == "__main__":
    lista_visitados = []
    tamanho = int(input("Informe o tamanho da matriz: "))
    repeticoes = int(input("Quantas vezes? "))
    for i in range(repeticoes):
        matriz = criar_matriz(tamanho)
        novoEstado = converter_string(matriz)
        lista_visitados = inserir_visitados(lista_visitados,matriz,novoEstado)
        
    print(lista_visitados)

