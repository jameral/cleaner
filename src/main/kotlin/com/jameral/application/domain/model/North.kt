package com.jameral.application.domain.model

class North : Direction {

    companion object {
        fun get(): Direction {
            return North()
        }
    }

    override fun move(position: Position) {
        position.y += 1;
    }

    override fun rotateRight(): Direction {
        return East.get()
    }

    override fun rotateLeft(): Direction {
        return West.get()
    }

    override fun toString(): String {
        return "N"
    }
}