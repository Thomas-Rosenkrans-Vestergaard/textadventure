package textadventure.ui;

public interface Option
{

	/**
	 * Returns the name of the {@link Option} item. The name is used when {@link Select}ing the {@link Option} item
	 * from the {@link UI}.
	 *
	 * @return The name of the {@link Option} item. The name is used when {@link Select}ing the {@link Option} item
	 * from the {@link UI}.
	 */
	String getOptionName();
}
