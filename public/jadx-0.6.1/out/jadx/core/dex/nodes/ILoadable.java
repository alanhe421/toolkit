package jadx.core.dex.nodes;

import jadx.core.utils.exceptions.DecodeException;

public interface ILoadable {
    void load() throws DecodeException;

    void unload();
}
