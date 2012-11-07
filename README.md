# rhodes-mprodigy

## Introduction
[Rhodes](http://www.motorola.com/Business/US-EN/RhoMobile+Suite/Rhodes) is an open source Ruby-based framework to rapidly build native apps for all major smartphone operating systems (iPhone, Android, RIM, Windows Mobile and Windows Phone 7).

[mprodigy](http://www.b2m-solutions.com/products-and-services/mprodigy/overview.aspx) Mobility Intelligence Platform provides information to enable effective strategic, tactical, and operational insights and decision-making for populations of mobile devices. This includes a comprehensive portfolio of operational, tactical and strategic analytics and dashboards.

[rhodes-mprodigy](https://github.com/B2MSolutions/rhodes-mprodigy) is an extension that auto-instruments Rhodes applications enabling strategic application analytics within mprodigy. It requires the device to be managed by mprodigy, for instance, using the android mprodigy client. If the device client is unavailable the instrumentation will have no effect to the running of the application.

If you need access to mprodigy integration servers to test you application instrumentation please [contact us](http://www.b2m-solutions.com/company/contact.aspx).

## Installation
[rhodes-mprodigy](https://github.com/B2MSolutions/rhodes-mprodigy) is a standard native extension for Rhodes and should be installed in the extensions folder of your application. 

Start a terminal window and cd to the root directory of your Rhodes application, then perform the following:

	> mkdir extensions
	> cd extensions
	> git clone git://github.com/B2MSolutions/rhodes-mprodigy.git mprodigy	

Now add the configuration to auto-instrument your application:
In *build.yaml* add the following:

```ruby	
extensions: ["mprodigy"]
```

Ensure your main application ruby file (the one that inherits from Rho::RhoApplication and is normally in app/application.rb) has the call to *Mprodigy::API::instrument* after the call to *super* as follows:

```ruby
require 'rho/rhoapplication'
require 'mprodigy'

class AppApplication < Rho::RhoApplication
  
  def initialize
    # Initialization before call to super
    super
	Mprodigy::API::instrument

    # Initialization after call to super...
  end	  
end
```

## Examples
We have forked the [Rhostore](https://github.com/B2MSolutions/store) sample application refered to in the [rhomobile documentation](http://docs.rhomobile.com/), and configured it to use rhodes-mprodigy. It is available [here](https://github.com/B2MSolutions/store).