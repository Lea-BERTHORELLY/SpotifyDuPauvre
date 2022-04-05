module MusicPlayer
{
	interface Player
	{
		string GetMusics();
		void AddMusic(string url);
		void DeleteMusic(string name);
		void ModifyMusic(string oldSong,string newSong);
		void PrintMusics(string music);
		bool Play(string music);
		void Pause();
		bool Stop();
		void SearchTitle(string title);
		void searchAuthor(string author);
	}
}