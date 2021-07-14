## Fontes de inspiração
-----------------------

- [Vim adventures](https://vim-adventures.com/)
- [Scratch](https://scratch.mit.edu/projects/editor/?tutorial=getStarted)
- [Selfless Heroes](https://store.steampowered.com/app/1273450/Selfless_Heroes/)

## Descrição
------------

Um jogo 2D que possui vários níveis desafiadores para os jogadores completarem
utilizando de uma linguagem de programação visual para movimentar um personagem
e cumprir um objetivo daquele nível. Essa linguagem visual seria composta de
blocos que o jogador pode combinar de maneiras distintas para produzir o
comportamento desejado. Os comandos possíveis nessa linguagem são:

- Andar para os lados (cima, baixo, direita, esquerda)
- Condicional (se, se não)
- Loop (enquanto, repete)
- Interagir

Além disso é possível visualizar o código visual numa linguagem de programação
escrita e em português.

Esse jogo também contará com um sistema de menus, cenários para os níveis de
jogo e design de personagens e objetos.

## Funcionalidades a serem desenvolvidas
----------------------------------------

- Menus
    - Menu principal
    - Menu com os níveis que permite a seleção do nível a ser jogado
    - Menu de configurações
- Interpretador da linguagem visual e escrita.
- Editor de blocos da linguagem de programação visual que permite a criação da lógica.
- Ferramenta que mostra uma versão em texto do código.

## Especificação da linguagem escrita
-------------------------------------

```
se <condição> {

} se não {
    se <condição> {

    }
}

enquanto <condição> {

}
```

```
enquanto está_em_cima_de_azul {

}
```
