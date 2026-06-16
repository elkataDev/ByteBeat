package com.github.elkataDev.bytebeat.config;

import com.github.elkataDev.bytebeat.model.Album;
import com.github.elkataDev.bytebeat.model.Artist;
import com.github.elkataDev.bytebeat.model.Song;
import com.github.elkataDev.bytebeat.model.User;
import com.github.elkataDev.bytebeat.repository.AlbumRepository;
import com.github.elkataDev.bytebeat.repository.ArtistRepository;
import com.github.elkataDev.bytebeat.repository.PlaylistRepository;
import com.github.elkataDev.bytebeat.repository.SongRepository;
import com.github.elkataDev.bytebeat.repository.UserRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Componente para el poblado inicial de datos (Data Seeding) en la base de datos.
 * * - @Component: Registra esta clase como un Bean de Spring para que se instancie automáticamente
 * e inyecte los repositorios necesarios en el constructor.
 * - CommandLineRunner: Interfaz que ejecuta el método 'run' una sola vez justo después de
 * que el servidor arranca y está listo.
 * - Propósito: Insertar usuarios, artistas, álbumes y canciones reales de prueba si la base
 * de datos está vacía, asegurando contenido listo para el desarrollo del Frontend.
 */

@Component
public class DataSeeder implements CommandLineRunner {

    private final ArtistRepository artistRepository;
    private final AlbumRepository albumRepository;
    private final SongRepository songRepository;
    private final UserRepository userRepository;
    private final PlaylistRepository playlistRepository;

    public DataSeeder(ArtistRepository artistRepository, AlbumRepository albumRepository, SongRepository songRepository,
            UserRepository userRepository,
            PlaylistRepository playlistRepository) {

        this.artistRepository = artistRepository;
        this.albumRepository = albumRepository;
        this.songRepository = songRepository;
        this.userRepository = userRepository;
        this.playlistRepository = playlistRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        //  Evitamos duplicados: Solo se ejecuta si la tabla de artistas está vacía
        if (artistRepository.count() == 0) {
            System.out.println("🌱 [Bytebeat Seed] Poblando la base de datos con música real...");

            // USUARIOS DE PRUEBA

            User admin = new User("admin", "admin@bytebeat.com", "admin123456", LocalDateTime.now(), "ADMIN");
            User oyente = new User("elkataDev", "kata@gmail.com", "kata123456", LocalDateTime.now(), "USER");
            userRepository.save(admin);
            userRepository.save(oyente);

            // ARTISTA: DAFT PUNK

            Artist daftPunk = new Artist(
                    "Daft Punk",
                    "Dúo francés de música electrónica fundado en París en 1993 por Thomas Bangalter y Guy-Manuel de Homem-Christo. Lograron gran popularidad en el estilo French House.",
                    "https://i.scdn.co/image/ab6761610000e5ebde00edec6e298da43dbfa4c3");
            artistRepository.save(daftPunk);

            // 💿 Álbum: Discovery (2001)
            Album discovery = new Album(
                    "Discovery",
                    LocalDate.of(2001, 3, 12),
                    "https://i.scdn.co/image/ab67616d0000b2732cd47fbf608aa868078ab2a3",
                    daftPunk);
            albumRepository.save(discovery);

            // 🎵 Canciones de Discovery
            Song d1 = new Song("One More Time", 320, "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3", 1,
                    1420, discovery);
            Song d2 = new Song("Aerodynamic", 212, "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-2.mp3", 2,
                    850, discovery);
            Song d3 = new Song("Digital Love", 298, "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-3.mp3", 3,
                    2100, discovery);
            Song d4 = new Song("Harder, Better, Faster, Stronger", 224,
                    "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-4.mp3", 4, 5300, discovery);
            songRepository.save(d1);
            songRepository.save(d2);
            songRepository.save(d3);
            songRepository.save(d4);

            // ARTISTA: MICHAEL JACKSON

            Artist michaelJackson = new Artist(
                    "Michael Jackson",
                    "Conocido como el 'Rey del Pop', fue un cantante, compositor, productor discográfico y bailarín estadounidense, considerado una de las figuras culturales más importantes del siglo XX.",
                    "https://i.scdn.co/image/ab6761610000e5eb743ec2900c1443685c4cf7e8");
            artistRepository.save(michaelJackson);

            // 💿 Álbum: Thriller (1982)
            Album thriller = new Album(
                    "Thriller",
                    LocalDate.of(1982, 11, 30),
                    "https://i.scdn.co/image/ab67616d0000b2734121faeeebaa3ebabb99629a",
                    michaelJackson);
            albumRepository.save(thriller);

            // 🎵 Canciones de Thriller
            Song mj1 = new Song("Wanna Be Startin' Somethin'", 362,
                    "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-5.mp3", 1, 9500, thriller);
            Song mj2 = new Song("Thriller", 357, "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-6.mp3", 4,
                    15400, thriller);
            Song mj3 = new Song("Beat It", 258, "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-7.mp3", 5,
                    23000, thriller);
            Song mj4 = new Song("Billie Jean", 294, "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-8.mp3", 6,
                    31200, thriller);
            songRepository.save(mj1);
            songRepository.save(mj2);
            songRepository.save(mj3);
            songRepository.save(mj4);

            // ARTISTA: ROSALÍA

            Artist rosalia = new Artist(
                    "Rosalía",
                    "Rosalía Vila Tobella, conocida monónimamente como Rosalía, es una cantautora y productora española que redefine el flamenco fusionándolo con música urbana y pop.",
                    "https://i.scdn.co/image/ab6761610000e5eb19e763f0190209df95bfa3eb");
            artistRepository.save(rosalia);

            // 💿 Álbum: MOTOMAMI (2022)
            Album motomami = new Album(
                    "MOTOMAMI",
                    LocalDate.of(2022, 3, 18),
                    "https://i.scdn.co/image/ab67616d0000b27393a4f66ec0fa7a147b19323b",
                    rosalia);
            albumRepository.save(motomami);

            // 🎵 Canciones de MOTOMAMI
            Song r1 = new Song("SAOKO", 137, "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-9.mp3", 1, 4100,
                    motomami);
            Song r2 = new Song("CANDY", 131, "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-10.mp3", 2, 2800,
                    motomami);
            Song r3 = new Song("HENTAI", 162, "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-11.mp3", 4, 3400,
                    motomami);
            Song r4 = new Song("BIZKCOCHITO", 109, "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-12.mp3", 7,
                    8900, motomami);
            songRepository.save(r1);
            songRepository.save(r2);
            songRepository.save(r3);
            songRepository.save(r4);

            System.out.println(
                    "✅ [Bytebeat Seed] ¡Base de datos poblada con 3 artistas, 3 álbumes, 12 canciones y 2 usuarios!");
        } else {
            System.out.println(
                    "ℹ️ [Bytebeat Seed] La base de datos ya contiene información. Se omite el volcado de datos inicial.");
        }
    }
}