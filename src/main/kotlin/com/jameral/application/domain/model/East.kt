package com.jameral.application.domain.model

class East : Direction {

    companion object {
        fun get() : Direction {
            return East()
        }
    }

    override fun move(position: Position) {
        position.x += 1;
    }

    override fun rotateRight() : Direction {
        return South.get()
    }

    override fun rotateLeft() : Direction {
        return North.get()
    }

    override fun toString(): String {
        return "E"
    }
}