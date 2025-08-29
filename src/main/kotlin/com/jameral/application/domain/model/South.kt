package com.jameral.application.domain.model

class South : Direction {

    companion object {
        fun get() : Direction {
            return South()
        }
    }

    override fun move(position: Position) {
        position.y -= 1;
    }

    override fun rotateRight() : Direction {
        return West.get()
    }

    override fun rotateLeft() : Direction {
        return East.get()
    }

    override fun toString(): String {
        return "S"
    }
}