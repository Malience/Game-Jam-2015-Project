#version 330 core

in vec2 texCoord0;

uniform vec3 R_ambient;
uniform sampler2D diffuse;

out vec4 fragColor;

void main()
{
	fragColor = texture2D(diffuse, texCoord0.xy) * vec4(R_ambient, 1);
}
