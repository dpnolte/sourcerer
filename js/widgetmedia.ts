/* generated @ 2018-11-09T18:39:25.723 */
import { element } from './element';
/// <reference path='./element.types.d.ts' />
/// <reference path='./layoutparams.types.d.ts' />
/// <reference path='./widgetmedia.types.d.ts' />

export const VideoView2 = (
  attributes?: WidgetMediaTypes.VideoView2Attributes & LayoutParamsTypes.LayoutParamAttributes,
  children?: Array<ElementTypes.ElementNode<unknown, LayoutParamsTypes.LayoutParamAttributes>>
): ElementTypes.ElementNode<WidgetMediaTypes.VideoView2Attributes, LayoutParamsTypes.LayoutParamAttributes> => {
  return element('videoView2', attributes, children);
};
export const MediaControlView2 = (
  attributes?: WidgetMediaTypes.MediaControlView2Attributes & LayoutParamsTypes.LayoutParamAttributes,
  children?: Array<ElementTypes.ElementNode<unknown, LayoutParamsTypes.LayoutParamAttributes>>
): ElementTypes.ElementNode<WidgetMediaTypes.MediaControlView2Attributes, LayoutParamsTypes.LayoutParamAttributes> => {
  return element('mediaControlView2', attributes, children);
};