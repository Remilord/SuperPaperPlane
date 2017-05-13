package com.mygdx.game;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;

public class HitboxListener implements ContactListener{
	private Niveau niveau;
	
	public HitboxListener(Niveau niveau) {
		this.niveau = niveau;
	}
	
	@Override
	public void endContact(Contact contact) {
			
	}
	
	@Override
	public void beginContact(Contact contact) {
	
	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
		// TODO Auto-generated method stub
		
	}
}
