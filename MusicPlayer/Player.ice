module MusicPlayer
{
	sequence<byte> bytes;
	interface Player
	{
		string GetMusics();
		//void AddMusic(string titre, string auteurs, string album, string cheminAcces, string urlStream, bytes musique);
		void AddMusic(int offset, bytes partiesMusique, string path);
		void DeleteMusic(string name);
		void RenameMusic(string oldName,string newName);
		void ReplaceMusic(string oldSong,string newSong);
		void PrintMusics(string music);
		bool Play(string music);
		void Pause();
		bool Stop();
		void SearchTitle(string title);
		void searchAuthor(string author);
		bytes SendParts(string titre, bytes parts);
	}
}