/* generated @ 2018-11-10T13:55:57.768 */
import { element } from './element';
/// <reference path='./element.types.d.ts' />
/// <reference path='./layoutparams.types.d.ts' />
/// <reference path='./viewslice.types.d.ts' />

export const SliceView = (
  attributes?: ViewSliceTypes.SliceViewAttributes & LayoutParamsTypes.LayoutParamAttributes,
  children?: Array<ElementTypes.ElementNode<unknown, LayoutParamsTypes.LayoutParamAttributes>>
): ElementTypes.ElementNode<ViewSliceTypes.SliceViewAttributes, LayoutParamsTypes.LayoutParamAttributes> => {
  return element('sliceView', attributes, children);
};
