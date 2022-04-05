# -*- coding: utf-8 -*-
#
# Copyright (c) ZeroC, Inc. All rights reserved.
#
#
# Ice version 3.7.7
#
# <auto-generated>
#
# Generated from file `Player.ice'
#
# Warning: do not edit this file.
#
# </auto-generated>
#

from sys import version_info as _version_info_
import Ice, IcePy

# Start of module MusicPlayer
_M_MusicPlayer = Ice.openModule('MusicPlayer')
__name__ = 'MusicPlayer'

_M_MusicPlayer._t_Player = IcePy.defineValue('::MusicPlayer::Player', Ice.Value, -1, (), False, True, None, ())

if 'PlayerPrx' not in _M_MusicPlayer.__dict__:
    _M_MusicPlayer.PlayerPrx = Ice.createTempClass()
    class PlayerPrx(Ice.ObjectPrx):

        def GetMusics(self, context=None):
            return _M_MusicPlayer.Player._op_GetMusics.invoke(self, ((), context))

        def GetMusicsAsync(self, context=None):
            return _M_MusicPlayer.Player._op_GetMusics.invokeAsync(self, ((), context))

        def begin_GetMusics(self, _response=None, _ex=None, _sent=None, context=None):
            return _M_MusicPlayer.Player._op_GetMusics.begin(self, ((), _response, _ex, _sent, context))

        def end_GetMusics(self, _r):
            return _M_MusicPlayer.Player._op_GetMusics.end(self, _r)

        def AddMusic(self, url, context=None):
            return _M_MusicPlayer.Player._op_AddMusic.invoke(self, ((url, ), context))

        def AddMusicAsync(self, url, context=None):
            return _M_MusicPlayer.Player._op_AddMusic.invokeAsync(self, ((url, ), context))

        def begin_AddMusic(self, url, _response=None, _ex=None, _sent=None, context=None):
            return _M_MusicPlayer.Player._op_AddMusic.begin(self, ((url, ), _response, _ex, _sent, context))

        def end_AddMusic(self, _r):
            return _M_MusicPlayer.Player._op_AddMusic.end(self, _r)

        def DeleteMusic(self, name, context=None):
            return _M_MusicPlayer.Player._op_DeleteMusic.invoke(self, ((name, ), context))

        def DeleteMusicAsync(self, name, context=None):
            return _M_MusicPlayer.Player._op_DeleteMusic.invokeAsync(self, ((name, ), context))

        def begin_DeleteMusic(self, name, _response=None, _ex=None, _sent=None, context=None):
            return _M_MusicPlayer.Player._op_DeleteMusic.begin(self, ((name, ), _response, _ex, _sent, context))

        def end_DeleteMusic(self, _r):
            return _M_MusicPlayer.Player._op_DeleteMusic.end(self, _r)

        def ModifyMusic(self, oldSong, newSong, context=None):
            return _M_MusicPlayer.Player._op_ModifyMusic.invoke(self, ((oldSong, newSong), context))

        def ModifyMusicAsync(self, oldSong, newSong, context=None):
            return _M_MusicPlayer.Player._op_ModifyMusic.invokeAsync(self, ((oldSong, newSong), context))

        def begin_ModifyMusic(self, oldSong, newSong, _response=None, _ex=None, _sent=None, context=None):
            return _M_MusicPlayer.Player._op_ModifyMusic.begin(self, ((oldSong, newSong), _response, _ex, _sent, context))

        def end_ModifyMusic(self, _r):
            return _M_MusicPlayer.Player._op_ModifyMusic.end(self, _r)

        def PrintMusics(self, music, context=None):
            return _M_MusicPlayer.Player._op_PrintMusics.invoke(self, ((music, ), context))

        def PrintMusicsAsync(self, music, context=None):
            return _M_MusicPlayer.Player._op_PrintMusics.invokeAsync(self, ((music, ), context))

        def begin_PrintMusics(self, music, _response=None, _ex=None, _sent=None, context=None):
            return _M_MusicPlayer.Player._op_PrintMusics.begin(self, ((music, ), _response, _ex, _sent, context))

        def end_PrintMusics(self, _r):
            return _M_MusicPlayer.Player._op_PrintMusics.end(self, _r)

        def Play(self, music, context=None):
            return _M_MusicPlayer.Player._op_Play.invoke(self, ((music, ), context))

        def PlayAsync(self, music, context=None):
            return _M_MusicPlayer.Player._op_Play.invokeAsync(self, ((music, ), context))

        def begin_Play(self, music, _response=None, _ex=None, _sent=None, context=None):
            return _M_MusicPlayer.Player._op_Play.begin(self, ((music, ), _response, _ex, _sent, context))

        def end_Play(self, _r):
            return _M_MusicPlayer.Player._op_Play.end(self, _r)

        def Pause(self, context=None):
            return _M_MusicPlayer.Player._op_Pause.invoke(self, ((), context))

        def PauseAsync(self, context=None):
            return _M_MusicPlayer.Player._op_Pause.invokeAsync(self, ((), context))

        def begin_Pause(self, _response=None, _ex=None, _sent=None, context=None):
            return _M_MusicPlayer.Player._op_Pause.begin(self, ((), _response, _ex, _sent, context))

        def end_Pause(self, _r):
            return _M_MusicPlayer.Player._op_Pause.end(self, _r)

        def Stop(self, context=None):
            return _M_MusicPlayer.Player._op_Stop.invoke(self, ((), context))

        def StopAsync(self, context=None):
            return _M_MusicPlayer.Player._op_Stop.invokeAsync(self, ((), context))

        def begin_Stop(self, _response=None, _ex=None, _sent=None, context=None):
            return _M_MusicPlayer.Player._op_Stop.begin(self, ((), _response, _ex, _sent, context))

        def end_Stop(self, _r):
            return _M_MusicPlayer.Player._op_Stop.end(self, _r)

        def SearchTitle(self, title, context=None):
            return _M_MusicPlayer.Player._op_SearchTitle.invoke(self, ((title, ), context))

        def SearchTitleAsync(self, title, context=None):
            return _M_MusicPlayer.Player._op_SearchTitle.invokeAsync(self, ((title, ), context))

        def begin_SearchTitle(self, title, _response=None, _ex=None, _sent=None, context=None):
            return _M_MusicPlayer.Player._op_SearchTitle.begin(self, ((title, ), _response, _ex, _sent, context))

        def end_SearchTitle(self, _r):
            return _M_MusicPlayer.Player._op_SearchTitle.end(self, _r)

        def searchAuthor(self, author, context=None):
            return _M_MusicPlayer.Player._op_searchAuthor.invoke(self, ((author, ), context))

        def searchAuthorAsync(self, author, context=None):
            return _M_MusicPlayer.Player._op_searchAuthor.invokeAsync(self, ((author, ), context))

        def begin_searchAuthor(self, author, _response=None, _ex=None, _sent=None, context=None):
            return _M_MusicPlayer.Player._op_searchAuthor.begin(self, ((author, ), _response, _ex, _sent, context))

        def end_searchAuthor(self, _r):
            return _M_MusicPlayer.Player._op_searchAuthor.end(self, _r)

        @staticmethod
        def checkedCast(proxy, facetOrContext=None, context=None):
            return _M_MusicPlayer.PlayerPrx.ice_checkedCast(proxy, '::MusicPlayer::Player', facetOrContext, context)

        @staticmethod
        def uncheckedCast(proxy, facet=None):
            return _M_MusicPlayer.PlayerPrx.ice_uncheckedCast(proxy, facet)

        @staticmethod
        def ice_staticId():
            return '::MusicPlayer::Player'
    _M_MusicPlayer._t_PlayerPrx = IcePy.defineProxy('::MusicPlayer::Player', PlayerPrx)

    _M_MusicPlayer.PlayerPrx = PlayerPrx
    del PlayerPrx

    _M_MusicPlayer.Player = Ice.createTempClass()
    class Player(Ice.Object):

        def ice_ids(self, current=None):
            return ('::Ice::Object', '::MusicPlayer::Player')

        def ice_id(self, current=None):
            return '::MusicPlayer::Player'

        @staticmethod
        def ice_staticId():
            return '::MusicPlayer::Player'

        def GetMusics(self, current=None):
            raise NotImplementedError("servant method 'GetMusics' not implemented")

        def AddMusic(self, url, current=None):
            raise NotImplementedError("servant method 'AddMusic' not implemented")

        def DeleteMusic(self, name, current=None):
            raise NotImplementedError("servant method 'DeleteMusic' not implemented")

        def ModifyMusic(self, oldSong, newSong, current=None):
            raise NotImplementedError("servant method 'ModifyMusic' not implemented")

        def PrintMusics(self, music, current=None):
            raise NotImplementedError("servant method 'PrintMusics' not implemented")

        def Play(self, music, current=None):
            raise NotImplementedError("servant method 'Play' not implemented")

        def Pause(self, current=None):
            raise NotImplementedError("servant method 'Pause' not implemented")

        def Stop(self, current=None):
            raise NotImplementedError("servant method 'Stop' not implemented")

        def SearchTitle(self, title, current=None):
            raise NotImplementedError("servant method 'SearchTitle' not implemented")

        def searchAuthor(self, author, current=None):
            raise NotImplementedError("servant method 'searchAuthor' not implemented")

        def __str__(self):
            return IcePy.stringify(self, _M_MusicPlayer._t_PlayerDisp)

        __repr__ = __str__

    _M_MusicPlayer._t_PlayerDisp = IcePy.defineClass('::MusicPlayer::Player', Player, (), None, ())
    Player._ice_type = _M_MusicPlayer._t_PlayerDisp

    Player._op_GetMusics = IcePy.Operation('GetMusics', Ice.OperationMode.Normal, Ice.OperationMode.Normal, False, None, (), (), (), ((), IcePy._t_string, False, 0), ())
    Player._op_AddMusic = IcePy.Operation('AddMusic', Ice.OperationMode.Normal, Ice.OperationMode.Normal, False, None, (), (((), IcePy._t_string, False, 0),), (), None, ())
    Player._op_DeleteMusic = IcePy.Operation('DeleteMusic', Ice.OperationMode.Normal, Ice.OperationMode.Normal, False, None, (), (((), IcePy._t_string, False, 0),), (), None, ())
    Player._op_ModifyMusic = IcePy.Operation('ModifyMusic', Ice.OperationMode.Normal, Ice.OperationMode.Normal, False, None, (), (((), IcePy._t_string, False, 0), ((), IcePy._t_string, False, 0)), (), None, ())
    Player._op_PrintMusics = IcePy.Operation('PrintMusics', Ice.OperationMode.Normal, Ice.OperationMode.Normal, False, None, (), (((), IcePy._t_string, False, 0),), (), None, ())
    Player._op_Play = IcePy.Operation('Play', Ice.OperationMode.Normal, Ice.OperationMode.Normal, False, None, (), (((), IcePy._t_string, False, 0),), (), ((), IcePy._t_bool, False, 0), ())
    Player._op_Pause = IcePy.Operation('Pause', Ice.OperationMode.Normal, Ice.OperationMode.Normal, False, None, (), (), (), None, ())
    Player._op_Stop = IcePy.Operation('Stop', Ice.OperationMode.Normal, Ice.OperationMode.Normal, False, None, (), (), (), ((), IcePy._t_bool, False, 0), ())
    Player._op_SearchTitle = IcePy.Operation('SearchTitle', Ice.OperationMode.Normal, Ice.OperationMode.Normal, False, None, (), (((), IcePy._t_string, False, 0),), (), None, ())
    Player._op_searchAuthor = IcePy.Operation('searchAuthor', Ice.OperationMode.Normal, Ice.OperationMode.Normal, False, None, (), (((), IcePy._t_string, False, 0),), (), None, ())

    _M_MusicPlayer.Player = Player
    del Player

# End of module MusicPlayer
