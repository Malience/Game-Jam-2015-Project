#version 330 core
#include "lighting.glh"

in vec2 texCoord0;
in vec3 normal0;
in vec3 worldPos0;
in vec3 shadowCoord0;

out vec4 fragColor;

uniform sampler2D diffuse;
uniform sampler2D shadowMap;

uniform DirectionalLight R_directionalLight;


void main()
{
	float visibility = 1.0;
	if(texture(shadowMap, shadowCoord0.xy).z < shadowCoord0.z)
	{
		visibility = 0.5;
	}
    fragColor = texture2D(diffuse, texCoord0.xy) * calcDirectionalLight(R_directionalLight, normalize(normal0), worldPos0) * visibility;
}
