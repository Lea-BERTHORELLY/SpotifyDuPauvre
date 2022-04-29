//
// Copyright (c) ZeroC, Inc. All rights reserved.
//
//
// Ice version 3.7.3
//
// <auto-generated>
//
// Generated from file `Player.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package MusicPlayer;

public interface Player extends com.zeroc.Ice.Object
{
    String GetMusics(com.zeroc.Ice.Current current);

    void AddMusic(int offset, byte[] partiesMusique, String path, String titre, String artistes, String album, com.zeroc.Ice.Current current);

    void AddMusicDatabase(String titre, String artistes, String album, com.zeroc.Ice.Current current);

    void DeleteMusic(String name, com.zeroc.Ice.Current current);

    void ModifyMusic(String musiqueAModifier, String nouveauTitre, String nouveauxArtistes, String nouvelAlbum, com.zeroc.Ice.Current current);

    void PrintMusics(String music, com.zeroc.Ice.Current current);

    boolean Play(String music, com.zeroc.Ice.Current current);

    String Pause(com.zeroc.Ice.Current current);

    boolean Stop(com.zeroc.Ice.Current current);

    void SearchTitle(String title, com.zeroc.Ice.Current current);

    void searchAuthor(String author, com.zeroc.Ice.Current current);

    /** @hidden */
    static final String[] _iceIds =
    {
        "::Ice::Object",
        "::MusicPlayer::Player"
    };

    @Override
    default String[] ice_ids(com.zeroc.Ice.Current current)
    {
        return _iceIds;
    }

    @Override
    default String ice_id(com.zeroc.Ice.Current current)
    {
        return ice_staticId();
    }

    static String ice_staticId()
    {
        return "::MusicPlayer::Player";
    }

    /**
     * @hidden
     * @param obj -
     * @param inS -
     * @param current -
     * @return -
    **/
    static java.util.concurrent.CompletionStage<com.zeroc.Ice.OutputStream> _iceD_GetMusics(Player obj, final com.zeroc.IceInternal.Incoming inS, com.zeroc.Ice.Current current)
    {
        com.zeroc.Ice.Object._iceCheckMode(null, current.mode);
        inS.readEmptyParams();
        String ret = obj.GetMusics(current);
        com.zeroc.Ice.OutputStream ostr = inS.startWriteParams();
        ostr.writeString(ret);
        inS.endWriteParams(ostr);
        return inS.setResult(ostr);
    }

    /**
     * @hidden
     * @param obj -
     * @param inS -
     * @param current -
     * @return -
    **/
    static java.util.concurrent.CompletionStage<com.zeroc.Ice.OutputStream> _iceD_AddMusic(Player obj, final com.zeroc.IceInternal.Incoming inS, com.zeroc.Ice.Current current)
    {
        com.zeroc.Ice.Object._iceCheckMode(null, current.mode);
        com.zeroc.Ice.InputStream istr = inS.startReadParams();
        int iceP_offset;
        byte[] iceP_partiesMusique;
        String iceP_path;
        String iceP_titre;
        String iceP_artistes;
        String iceP_album;
        iceP_offset = istr.readInt();
        iceP_partiesMusique = istr.readByteSeq();
        iceP_path = istr.readString();
        iceP_titre = istr.readString();
        iceP_artistes = istr.readString();
        iceP_album = istr.readString();
        inS.endReadParams();
        obj.AddMusic(iceP_offset, iceP_partiesMusique, iceP_path, iceP_titre, iceP_artistes, iceP_album, current);
        return inS.setResult(inS.writeEmptyParams());
    }

    /**
     * @hidden
     * @param obj -
     * @param inS -
     * @param current -
     * @return -
    **/
    static java.util.concurrent.CompletionStage<com.zeroc.Ice.OutputStream> _iceD_AddMusicDatabase(Player obj, final com.zeroc.IceInternal.Incoming inS, com.zeroc.Ice.Current current)
    {
        com.zeroc.Ice.Object._iceCheckMode(null, current.mode);
        com.zeroc.Ice.InputStream istr = inS.startReadParams();
        String iceP_titre;
        String iceP_artistes;
        String iceP_album;
        iceP_titre = istr.readString();
        iceP_artistes = istr.readString();
        iceP_album = istr.readString();
        inS.endReadParams();
        obj.AddMusicDatabase(iceP_titre, iceP_artistes, iceP_album, current);
        return inS.setResult(inS.writeEmptyParams());
    }

    /**
     * @hidden
     * @param obj -
     * @param inS -
     * @param current -
     * @return -
    **/
    static java.util.concurrent.CompletionStage<com.zeroc.Ice.OutputStream> _iceD_DeleteMusic(Player obj, final com.zeroc.IceInternal.Incoming inS, com.zeroc.Ice.Current current)
    {
        com.zeroc.Ice.Object._iceCheckMode(null, current.mode);
        com.zeroc.Ice.InputStream istr = inS.startReadParams();
        String iceP_name;
        iceP_name = istr.readString();
        inS.endReadParams();
        obj.DeleteMusic(iceP_name, current);
        return inS.setResult(inS.writeEmptyParams());
    }

    /**
     * @hidden
     * @param obj -
     * @param inS -
     * @param current -
     * @return -
    **/
    static java.util.concurrent.CompletionStage<com.zeroc.Ice.OutputStream> _iceD_ModifyMusic(Player obj, final com.zeroc.IceInternal.Incoming inS, com.zeroc.Ice.Current current)
    {
        com.zeroc.Ice.Object._iceCheckMode(null, current.mode);
        com.zeroc.Ice.InputStream istr = inS.startReadParams();
        String iceP_musiqueAModifier;
        String iceP_nouveauTitre;
        String iceP_nouveauxArtistes;
        String iceP_nouvelAlbum;
        iceP_musiqueAModifier = istr.readString();
        iceP_nouveauTitre = istr.readString();
        iceP_nouveauxArtistes = istr.readString();
        iceP_nouvelAlbum = istr.readString();
        inS.endReadParams();
        obj.ModifyMusic(iceP_musiqueAModifier, iceP_nouveauTitre, iceP_nouveauxArtistes, iceP_nouvelAlbum, current);
        return inS.setResult(inS.writeEmptyParams());
    }

    /**
     * @hidden
     * @param obj -
     * @param inS -
     * @param current -
     * @return -
    **/
    static java.util.concurrent.CompletionStage<com.zeroc.Ice.OutputStream> _iceD_PrintMusics(Player obj, final com.zeroc.IceInternal.Incoming inS, com.zeroc.Ice.Current current)
    {
        com.zeroc.Ice.Object._iceCheckMode(null, current.mode);
        com.zeroc.Ice.InputStream istr = inS.startReadParams();
        String iceP_music;
        iceP_music = istr.readString();
        inS.endReadParams();
        obj.PrintMusics(iceP_music, current);
        return inS.setResult(inS.writeEmptyParams());
    }

    /**
     * @hidden
     * @param obj -
     * @param inS -
     * @param current -
     * @return -
    **/
    static java.util.concurrent.CompletionStage<com.zeroc.Ice.OutputStream> _iceD_Play(Player obj, final com.zeroc.IceInternal.Incoming inS, com.zeroc.Ice.Current current)
    {
        com.zeroc.Ice.Object._iceCheckMode(null, current.mode);
        com.zeroc.Ice.InputStream istr = inS.startReadParams();
        String iceP_music;
        iceP_music = istr.readString();
        inS.endReadParams();
        boolean ret = obj.Play(iceP_music, current);
        com.zeroc.Ice.OutputStream ostr = inS.startWriteParams();
        ostr.writeBool(ret);
        inS.endWriteParams(ostr);
        return inS.setResult(ostr);
    }

    /**
     * @hidden
     * @param obj -
     * @param inS -
     * @param current -
     * @return -
    **/
    static java.util.concurrent.CompletionStage<com.zeroc.Ice.OutputStream> _iceD_Pause(Player obj, final com.zeroc.IceInternal.Incoming inS, com.zeroc.Ice.Current current)
    {
        com.zeroc.Ice.Object._iceCheckMode(null, current.mode);
        inS.readEmptyParams();
        String ret = obj.Pause(current);
        com.zeroc.Ice.OutputStream ostr = inS.startWriteParams();
        ostr.writeString(ret);
        inS.endWriteParams(ostr);
        return inS.setResult(ostr);
    }

    /**
     * @hidden
     * @param obj -
     * @param inS -
     * @param current -
     * @return -
    **/
    static java.util.concurrent.CompletionStage<com.zeroc.Ice.OutputStream> _iceD_Stop(Player obj, final com.zeroc.IceInternal.Incoming inS, com.zeroc.Ice.Current current)
    {
        com.zeroc.Ice.Object._iceCheckMode(null, current.mode);
        inS.readEmptyParams();
        boolean ret = obj.Stop(current);
        com.zeroc.Ice.OutputStream ostr = inS.startWriteParams();
        ostr.writeBool(ret);
        inS.endWriteParams(ostr);
        return inS.setResult(ostr);
    }

    /**
     * @hidden
     * @param obj -
     * @param inS -
     * @param current -
     * @return -
    **/
    static java.util.concurrent.CompletionStage<com.zeroc.Ice.OutputStream> _iceD_SearchTitle(Player obj, final com.zeroc.IceInternal.Incoming inS, com.zeroc.Ice.Current current)
    {
        com.zeroc.Ice.Object._iceCheckMode(null, current.mode);
        com.zeroc.Ice.InputStream istr = inS.startReadParams();
        String iceP_title;
        iceP_title = istr.readString();
        inS.endReadParams();
        obj.SearchTitle(iceP_title, current);
        return inS.setResult(inS.writeEmptyParams());
    }

    /**
     * @hidden
     * @param obj -
     * @param inS -
     * @param current -
     * @return -
    **/
    static java.util.concurrent.CompletionStage<com.zeroc.Ice.OutputStream> _iceD_searchAuthor(Player obj, final com.zeroc.IceInternal.Incoming inS, com.zeroc.Ice.Current current)
    {
        com.zeroc.Ice.Object._iceCheckMode(null, current.mode);
        com.zeroc.Ice.InputStream istr = inS.startReadParams();
        String iceP_author;
        iceP_author = istr.readString();
        inS.endReadParams();
        obj.searchAuthor(iceP_author, current);
        return inS.setResult(inS.writeEmptyParams());
    }

    /** @hidden */
    final static String[] _iceOps =
    {
        "AddMusic",
        "AddMusicDatabase",
        "DeleteMusic",
        "GetMusics",
        "ModifyMusic",
        "Pause",
        "Play",
        "PrintMusics",
        "SearchTitle",
        "Stop",
        "ice_id",
        "ice_ids",
        "ice_isA",
        "ice_ping",
        "searchAuthor"
    };

    /** @hidden */
    @Override
    default java.util.concurrent.CompletionStage<com.zeroc.Ice.OutputStream> _iceDispatch(com.zeroc.IceInternal.Incoming in, com.zeroc.Ice.Current current)
        throws com.zeroc.Ice.UserException
    {
        int pos = java.util.Arrays.binarySearch(_iceOps, current.operation);
        if(pos < 0)
        {
            throw new com.zeroc.Ice.OperationNotExistException(current.id, current.facet, current.operation);
        }

        switch(pos)
        {
            case 0:
            {
                return _iceD_AddMusic(this, in, current);
            }
            case 1:
            {
                return _iceD_AddMusicDatabase(this, in, current);
            }
            case 2:
            {
                return _iceD_DeleteMusic(this, in, current);
            }
            case 3:
            {
                return _iceD_GetMusics(this, in, current);
            }
            case 4:
            {
                return _iceD_ModifyMusic(this, in, current);
            }
            case 5:
            {
                return _iceD_Pause(this, in, current);
            }
            case 6:
            {
                return _iceD_Play(this, in, current);
            }
            case 7:
            {
                return _iceD_PrintMusics(this, in, current);
            }
            case 8:
            {
                return _iceD_SearchTitle(this, in, current);
            }
            case 9:
            {
                return _iceD_Stop(this, in, current);
            }
            case 10:
            {
                return com.zeroc.Ice.Object._iceD_ice_id(this, in, current);
            }
            case 11:
            {
                return com.zeroc.Ice.Object._iceD_ice_ids(this, in, current);
            }
            case 12:
            {
                return com.zeroc.Ice.Object._iceD_ice_isA(this, in, current);
            }
            case 13:
            {
                return com.zeroc.Ice.Object._iceD_ice_ping(this, in, current);
            }
            case 14:
            {
                return _iceD_searchAuthor(this, in, current);
            }
        }

        assert(false);
        throw new com.zeroc.Ice.OperationNotExistException(current.id, current.facet, current.operation);
    }
}