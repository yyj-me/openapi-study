package org.thinker.oauth.provider.parser;

import org.thinker.oauth.provider.util.OAuthException;

public interface RequestParsing {

	public abstract void parsing() throws OAuthException;

}