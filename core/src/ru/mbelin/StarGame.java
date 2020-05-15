package ru.mbelin;

import com.badlogic.gdx.Game;
import ru.mbelin.screen.MenuScreen;

public class StarGame extends Game {
	@Override
	public void create() {
		setScreen(new MenuScreen(this));
	}
}
