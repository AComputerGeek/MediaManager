/**
 * This is an interface.
 *
 * @author  Amir Armion
 * @version V.01
 */
public interface FileManager
{
    void save(final String path, final String fileName);

    void delete(final String path, final String fileName);
}
