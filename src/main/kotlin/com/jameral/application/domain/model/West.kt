package com.jameral.application.domain.model

class West : Direction {

    companion object {
        fun get(): Direction {
            return West()
        }
    }

    override fun move(position: Position) {
        position.x -= 1;
    }

    override fun rotateRight(): Direction {
        return North.get()
    }

    override fun rotateLeft(): Direction {
        return South.get()
    }

    override fun toString(): String {
        return "W"
    }
}